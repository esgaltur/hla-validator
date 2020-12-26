package cz.sosnovich.hlavalidator.hlavalidator.stages;

import cz.sosnovich.hlavalidator.hlavalidator.controllers.MainController;
import cz.sosnovich.hlavalidator.hlavalidator.events.StageReadyEvent;
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
     * @param fxWeaver
     */
    @Autowired
    public PrimaryStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    /**
     * @param event
     */
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        Scene scene = new Scene(fxWeaver.loadView(MainController.class));
        stage.setScene(scene);
        stage.show();
    }
}
