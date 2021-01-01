package cz.esgaltur.hlavalidator.hlavalidator.events;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 *
 */
public class StageReadyEvent extends ApplicationEvent {

    /**
     *
     */
    public final transient Stage stage;

    /**
     * @param stage JavaFX Stage
     */
    public StageReadyEvent(Stage stage) {
        super(stage);
        this.stage = stage;
    }
}
