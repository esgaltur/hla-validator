package cz.esgaltur.hlavalidator.hlavalidator.stages;

import cz.esgaltur.hlavalidator.hlavalidator.controllers.MainController;
import cz.esgaltur.hlavalidator.hlavalidator.events.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {
    /**
     *
     */
    private final FxWeaver fxWeaver;

    /**
     * @param fxWeaver Bean from the  AppConfig
     */
    @Autowired
    public PrimaryStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    /**
     * @param event StageReadyEvent from the JavaFX Application
     */
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        Scene scene = new Scene(fxWeaver.loadView(MainController.class));
        stage.setScene(scene);
        stage.show();
    }
}
