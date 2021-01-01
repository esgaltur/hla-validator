package cz.esgaltur.hlavalidator.hlavalidator.components.validators;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp.validators.NMDPLocusNameValidator;

/**
 * @author Dmitriy Sosnovich
 */
public class LocusNMDPTextValidator extends ValidatorBase {

    /**
     * @param message
     */
    public LocusNMDPTextValidator(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void eval() {
        String srcText = ((JFXTextField) getSrcControl()).getText();
        hasErrors.set(!NMDPLocusNameValidator.isValid(srcText));
    }
}
