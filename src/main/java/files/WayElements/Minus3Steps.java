package files.WayElements;

import files.Events.FinishEvent;
import files.Events.StepEvent;
import files.PlayerController;
import javafx.event.Event;
import javafx.scene.image.Image;

public class Minus3Steps extends WayElement {

    public Minus3Steps() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/Minus3Steps.png");
    }

    @Override
    public Event getEvent() {
        return new StepEvent(this, PlayerController.getInstance().getCurrentPlayer(),-3);
    }

    @Override
    public Image getMiniImage() {
        return new Image("/images/Optimazed/MinusStepsOptimized.png");
    }

    @Override
    public String toString() {
        return "Minus3Steps{}";
    }
}
