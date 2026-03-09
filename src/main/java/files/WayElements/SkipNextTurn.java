package files.WayElements;

import files.Events.SkipTurnEvent;
import files.PlayerController;
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
        return new SkipTurnEvent(this, PlayerController.getInstance().getCurrentPlayer());
    }

    @Override
    public String toString() {
        return "SkipNextTurn{}";
    }
}
