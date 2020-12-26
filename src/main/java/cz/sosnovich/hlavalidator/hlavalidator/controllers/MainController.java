package cz.sosnovich.hlavalidator.hlavalidator.controllers;


import com.jfoenix.controls.*;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.HlaNomReader;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.HlaNomRecord;
import cz.sosnovich.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.sosnovich.hlavalidator.hlavalidator.nmdp_web_service.client.api.DefaultApi;
import cz.sosnovich.hlavalidator.hlavalidator.nmdp_web_service.client.model.ValidatedTypingValidationResultOneOf;
import cz.sosnovich.hlavalidator.hlavalidator.nmdp_web_service.client.model.ValidationResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import lombok.extern.apachecommons.CommonsLog;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
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
     * @param hlaNomReader
     * @param defaultApi
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
                                r.getDateDeleted().equals(Date.from(Instant.ofEpochSecond(0))) &&
                                r.getValue().equals(txtLocusValue.getText())).findAny();
                break;
            case DNA:
                record =
                        hlaNomRecords.stream().filter(r -> r.getLocus().getValue()
                                .name().concat("*").equals(txtLocus.getText()) &&
                                r.getDateDeleted().equals(Date.from(Instant.ofEpochSecond(0))) &&
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
        }

    }

    /**
     * @return
     */
    private ValidatedTypingValidationResultOneOf validateByNMDP() {
        ValidatedTypingValidationResultOneOf validationResult;
        String imgtVersion = defaultApi.gETImgtHlaReleases().split("\n")[0].split(" ")[0];
        validationResult = defaultApi.gETValidate(imgtVersion, txtLocus.getText().concat(txtLocusValue.getText()));
        return validationResult;
    }
}
