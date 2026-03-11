package files.WayElements;

import files.Events.FinishEvent;
import files.PlayerController;
import javafx.event.Event;
import javafx.scene.image.Image;

public class Finish extends WayElement {

    public Finish() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/Finish.png");
    }

    @Override
    public Event getEvent() {
        return new FinishEvent(this);
    }

    @Override
    public String toString() {
        return "Finish{}";
    }

}
