package cz.sosnovich.hlavalidator.hlavalidator;

import cz.sosnovich.hlavalidator.hlavalidator.application.SpringBootJavaFxHlaValidatorApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dmitriy Sosnovich
 */
@SpringBootApplication
public class HlaValidatorApplication {

    public static void main(String[] args) {
        Application.launch(SpringBootJavaFxHlaValidatorApplication.class, args);

    }


}
