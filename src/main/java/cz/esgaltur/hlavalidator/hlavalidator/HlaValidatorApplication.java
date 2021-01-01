package cz.esgaltur.hlavalidator.hlavalidator;

import cz.esgaltur.hlavalidator.hlavalidator.application.SpringBootJavaFxHlaValidatorApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dmitriy esgaltur
 */
@SpringBootApplication
public class HlaValidatorApplication {

    public static void main(String[] args) {
        Application.launch(SpringBootJavaFxHlaValidatorApplication.class, args);

    }


}
