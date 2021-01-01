package cz.esgaltur.hlavalidator.hlavalidator.nmdp.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NMDPLocusNameValidatorTest {

    @Test
    public void valid_basic1() {
        String nmdpLocusName = "HLA-A*";
        Assertions.assertTrue(NMDPLocusNameValidator.isValid(nmdpLocusName));
    }

    @Test
    public void valid_basic2() {
        String nmdpLocusName = "HLA-DRB1*";
        Assertions.assertTrue(NMDPLocusNameValidator.isValid(nmdpLocusName));
    }

    @Test
    public void invalid_basic1() {
        String nmdpLocusName = "HLA-1A*";
        Assertions.assertFalse(NMDPLocusNameValidator.isValid(nmdpLocusName));
    }

    @Test
    public void invalid_basic2() {
        String nmdpLocusName = "HLA-DRB0*";
        Assertions.assertFalse(NMDPLocusNameValidator.isValid(nmdpLocusName));
    }

}
