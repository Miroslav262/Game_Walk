package files.Events;

import files.Panes.EventPanes.TotalSwapPane;
import files.PlayerController;
import files.WayElements.WayElement;
import javafx.event.EventType;

public class TotalSwapEvent extends WayElementEvent {

    public static final EventType<TotalSwapEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "TOTAL_SWAP_EVENT");

    public TotalSwapEvent(WayElement element) {
        super(TYPE, element);
        PlayerController.totalSwap();
        System.out.println(PlayerController.getInstance().getPlayers());
    }
}
