package cz.esgaltur.hlavalidator.hlavalidator.controllers;


import com.jfoenix.controls.*;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.HlaNomReader;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.HlaNomRecord;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp_web_service.client.api.DefaultApi;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp_web_service.client.model.ValidatedTypingValidationResultOneOf;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp_web_service.client.model.ValidationResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import lombok.extern.apachecommons.CommonsLog;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Component
@FxmlView
@CommonsLog
public class MainController {
    private final HlaNomReader hlaNomReader;
    private final DefaultApi defaultApi;
    private final List<HlaNomRecord> hlaNomRecords;
    @FXML
    public JFXButton btnValidate;
    @FXML
    public JFXComboBox<LocusType> cmbLocusType;
    @FXML
    public JFXProgressBar pbProgress;
    @FXML
    public JFXTextArea txtLocusValue;
    @FXML
    public JFXTextField txtLocus;
    @FXML
    public Label lblResult;

    /**
     * @param hlaNomReader Bean for the reading of the hla_nom file
     * @param defaultApi   Generated API for calling the NMDP API for validation of the NMDP code
     */
    @Autowired
    public MainController(HlaNomReader hlaNomReader, DefaultApi defaultApi) {
        this.hlaNomReader = hlaNomReader;
        this.defaultApi = defaultApi;
        hlaNomRecords = new ArrayList<>();
    }

    /**
     *
     */
    @FXML
    public void initialize() {

        new Thread(() -> {
            Optional<List<HlaNomRecord>> hlaNomRecordsOpt = hlaNomReader.readFromUrl(pbProgress);
            hlaNomRecordsOpt.ifPresent(nomRecords ->
                    this.hlaNomRecords.addAll(nomRecords.stream()
                            .filter(Objects::nonNull).collect(Collectors.toList())));
        }).start();

        ObservableList<LocusType> locusTypes = FXCollections.observableArrayList(LocusType.HLA,
                LocusType.DNA, LocusType.NMDP_CODE);
        cmbLocusType.setItems(locusTypes);


        new Thread(() -> btnValidate.setOnAction(actionEvent -> isHlaDnaValid())).start();
    }

    /**
     *
     */
    private void isHlaDnaValid() {
        log.info(cmbLocusType.getValue().name());
        log.info(txtLocus.getText());
        log.info(txtLocusValue.getText());
        Optional<HlaNomRecord> record = Optional.empty();
        ValidatedTypingValidationResultOneOf validationResult = new ValidationResult();
        switch (cmbLocusType.getValue()) {

            case UNKNOWN:
                record = Optional.empty();
                break;
            case HLA:
                record =
                        hlaNomRecords.stream().filter(r -> r.getLocus().getValue()
                                .name().equals(txtLocus.getText()) &&
                                !r.isDeleted() &&
                                r.getValue().equals(txtLocusValue.getText())).findAny();
                break;
            case DNA:
                record =
                        hlaNomRecords.stream().filter(r -> r.getLocus().getValue()
                                .name().concat("*").equals(txtLocus.getText()) &&
                                !r.isDeleted() &&
                                r.getValue().equals(txtLocusValue.getText())).findAny();
                break;
            case NMDP_CODE:
                validationResult = validateByNMDP();
                break;
            default:
        }


        if (record.isPresent() || Boolean.TRUE.equals(((ValidationResult) validationResult).getValid())) {

            lblResult.setTextFill(Color.GREEN);
            lblResult.setText("Valid");
            record.ifPresent(hlaNomRecord -> log.info(hlaNomRecord.toString()));
            Optional.of((ValidationResult) validationResult).ifPresent(r -> log.info(r.toString()));
        } else {
            lblResult.setTextFill(Color.RED);
            lblResult.setText("InValid");
            Optional.of((ValidationResult) validationResult).ifPresent(r -> log.info(r.toString()));
        }

    }

    /**
     * @return ValidatedTypingValidationResultOneOf
     */
    private ValidatedTypingValidationResultOneOf validateByNMDP() {
        ValidatedTypingValidationResultOneOf validationResult;
        String imgtVersion = defaultApi.gETImgtHlaReleases().split("\n")[0].split(" ")[0];
        validationResult = defaultApi.gETValidate(imgtVersion, txtLocus.getText().concat(txtLocusValue.getText()));
        return validationResult;
    }
}
