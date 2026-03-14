package files.WayElements;

import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public abstract class WayElement extends Pane {
    public abstract Image getImage();
    public abstract Event getEvent();
    public abstract Image getMiniImage();
}
