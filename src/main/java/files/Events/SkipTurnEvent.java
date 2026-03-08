package files.Events;

import files.WayElements.WayElement;
import javafx.event.EventType;

public class SkipTurnEvent extends WayElementEvent {

    public static final EventType<SkipTurnEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "SKIP_TURN_EVENT");

    public SkipTurnEvent(WayElement element) {
        super(TYPE, element);
    }
}
