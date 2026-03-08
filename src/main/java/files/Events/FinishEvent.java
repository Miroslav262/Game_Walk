package files.Events;

import files.WayElements.WayElement;
import javafx.event.EventType;

public class FinishEvent extends WayElementEvent {

    public static final EventType<FinishEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "FINISH_EVENT");

    public FinishEvent(WayElement element) {
        super(TYPE, element);
    }
}
