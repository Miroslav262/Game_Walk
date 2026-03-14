package files.WayElements;

import files.Events.SkipAnotherPlayerTurnEvent;
import javafx.event.Event;
import javafx.scene.image.Image;

public class SkipAnotherPlayerTurn extends WayElement {

    public SkipAnotherPlayerTurn() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/SkipAnotherPlayerTurn.png");
    }

    @Override
    public Event getEvent() {
        return new SkipAnotherPlayerTurnEvent(this);
    }

    @Override
    public Image getMiniImage() {
        return new Image("/images/Optimazed/SkipAnotherPlayerTurnOptimized.png");
    }

    @Override
    public String toString() {
        return "SkipAnotherPlayerTurn{}";
    }
}
