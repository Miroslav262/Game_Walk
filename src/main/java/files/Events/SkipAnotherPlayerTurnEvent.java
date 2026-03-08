package files.Events;

import files.WayElements.WayElement;
import javafx.event.EventType;

public class SkipAnotherPlayerTurnEvent extends WayElementEvent {

    public static final EventType<SkipAnotherPlayerTurnEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "SKIP_ANOTHER_PLAYER_TURN_EVENT");

    public SkipAnotherPlayerTurnEvent(WayElement element) {
        super(TYPE, element);
    }
}

