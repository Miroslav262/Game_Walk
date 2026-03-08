package files.Events;

import files.WayElements.WayElement;
import javafx.event.EventType;

public class TotalSwapEvent extends WayElementEvent {

    public static final EventType<SkipAnotherPlayerTurnEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "TOTAL_SWAP_EVENT");

    public TotalSwapEvent(WayElement element) {
        super(TYPE, element);
    }
}
