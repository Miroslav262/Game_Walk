package files.WayElements;

import files.Events.QuestionEvent;
import javafx.event.Event;
import javafx.scene.image.Image;

public class Start extends WayElement {

    public Start() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/Start.png");
    }

    @Override
    public Event getEvent() {
        return null;
    }

    @Override
    public String toString() {
        return "Start{}";
    }
}
