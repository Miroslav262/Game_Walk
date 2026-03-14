package files.WayElements;

import files.Events.QuestionEvent;
import files.Events.TotalSwapEvent;
import javafx.event.Event;
import javafx.scene.image.Image;

public class TotalSwap extends WayElement {

    public TotalSwap() {
    }

    @Override
    public Image getImage() {
        return new Image("/images/TotalSwap.png");
    }

    @Override
    public Event getEvent() {
        return new TotalSwapEvent(this);
    }

    @Override
    public Image getMiniImage() {
        return new Image("/images/Optimazed/TotalSwapOptimized.png");
    }

    @Override
    public String toString() {
        return "TotalSwap{}";
    }
}
