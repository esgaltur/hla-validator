package cz.esgaltur.hlavalidator.hlavalidator.application;

import cz.esgaltur.hlavalidator.hlavalidator.HlaValidatorApplication;
import cz.esgaltur.hlavalidator.hlavalidator.events.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 */
public class SpringBootJavaFxHlaValidatorApplication extends Application {
    /**
     *
     */
    private ConfigurableApplicationContext context;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(HlaValidatorApplication.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) {
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}
