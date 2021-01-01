package cz.esgaltur.hlavalidator.hlavalidator.controllers;


import com.jfoenix.controls.*;
import cz.esgaltur.hlavalidator.hlavalidator.components.validators.LocusNMDPTextValidator;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.HlaNomReader;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.HlaNomRecord;
import cz.esgaltur.hlavalidator.hlavalidator.hla_nom.enums.locus.LocusType;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp.nmdp_web_service.client.api.DefaultApi;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp.nmdp_web_service.client.model.ValidatedTypingValidationResultOneOf;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp.nmdp_web_service.client.model.ValidationResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import lombok.extern.apachecommons.CommonsLog;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

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

    private static final String LINE = "-------";
    private static final String OUT = "------->OUT";

    /**
     * @param hlaNomReader Bean for the reading of the hla_nom file
     */
    @Autowired
    public MainController(HlaNomReader hlaNomReader) {
        this.hlaNomReader = hlaNomReader;
        this.defaultApi = new DefaultApi();
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

        cmbLocusType.valueProperty().addListener((observableValue, locusType, t1) -> {
            if (observableValue.getValue().ordinal() == LocusType.NMDP_CODE.ordinal()) {
                LocusNMDPTextValidator locusNMDPTextValidator = new LocusNMDPTextValidator("Invalid NMDP locus name");
                txtLocus.setValidators(locusNMDPTextValidator);
            } else {
                txtLocus.resetValidation();
                txtLocus.getValidators().clear();
            }

        });
        new Thread(() -> btnValidate.setOnAction(actionEvent -> isHlaDnaValid())).start();
    }

    /**
     *
     */
    private void isHlaDnaValid() {
        if (!txtLocus.validate()) {
            return;
        }
        log.info("<------- IN");
        log.info(cmbLocusType.getValue().name());
        log.info(txtLocus.getText());
        log.info(txtLocusValue.getText());
        log.info(LINE);

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
            record.ifPresent(hlaNomRecord -> {
                log.info(OUT);
                log.info(hlaNomRecord.toString());
                log.info(LINE);
            });
            Optional.of((ValidationResult) validationResult).ifPresent(r -> {
                log.info(OUT);
                log.info(r.toString());
                log.info(LINE);
            });
        } else {
            lblResult.setTextFill(Color.RED);
            lblResult.setText("Invalid");
            Optional.of((ValidationResult) validationResult).ifPresent(r -> {
                log.info(OUT);
                log.info(r.toString());
                log.info(LINE);
            });
        }

    }

    /**
     * @return ValidatedTypingValidationResultOneOf
     */
    private ValidatedTypingValidationResultOneOf validateByNMDP() {
        ValidationResult validationResult = new ValidationResult();
        String imgtVersion = defaultApi.getImgtHlaReleases().split("\n")[0].split(" ")[0];
        try {
            validationResult = defaultApi.getValidate(imgtVersion, txtLocus.getText().concat(txtLocusValue.getText()));
            return validationResult;
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()) {
                log.error("Bad Request", e);
                validationResult.setValid(false);
                validationResult.setHistoricallyValid(false);
                validationResult.setDescription(e.getResponseBodyAsString());
                validationResult.setConditions(Collections.emptyList());
            }
            return validationResult;
        }

    }
}
