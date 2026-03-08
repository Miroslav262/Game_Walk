package files.WayElements;

import files.Events.QuestionEvent;
import files.Events.SkipTurnEvent;
import javafx.event.Event;
import javafx.scene.image.Image;

public class SkipNextTurn extends WayElement {

    public SkipNextTurn() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/SkipNextTurn.png");
    }

    @Override
    public Event getEvent() {
        return new SkipTurnEvent(this);
    }

    @Override
    public String toString() {
        return "SkipNextTurn{}";
    }
}
