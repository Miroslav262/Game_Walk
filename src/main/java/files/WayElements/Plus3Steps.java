package files.WayElements;

import files.Events.StepEvent;
import javafx.event.Event;
import javafx.scene.image.Image;

public class Plus3Steps extends WayElement {

    public Plus3Steps() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/Plus3Steps.png");
    }

    @Override
    public Event getEvent() {
        return new StepEvent(this, 3);
    }

    @Override
    public String toString() {
        return "Plus3Steps{}";
    }
}
