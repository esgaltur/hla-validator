package cz.esgaltur.hlavalidator.hlavalidator.nmdp.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class NMDPLocusNameValidator {
    //    private static final String REGEX = "^HLA-[A-Z]{1}[1-9]\\*$|^HLA-[A-Z]{2}[1-9]\\*$|^HLA-[A-Z]{3}[1-9]\\*$|^HLA-[A-Z]{4}[1-9]\\*$";
    private static final String REGEX = "^HLA-[A-Z]{1}\\*$|" +
            "^HLA-[A-Z]{3}[1-9]\\*$|" +
            "^HLA-[A-Z]{3}\\*$|" +
            "^HLA-[A-Z]{4}\\*$|";


    public static boolean isValid(String nmdpLocusName) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(nmdpLocusName);
        return matcher.matches();
    }
}
