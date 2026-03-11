package files.Events;

import files.Panes.EventPanes.FinishPane;

import files.Player;
import files.PlayerController;
import files.WayElements.WayElement;
import javafx.event.EventType;

public class FinishEvent extends WayElementEvent {

    public static final EventType<FinishEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "FINISH_EVENT");

    public FinishEvent(WayElement element) {
        super(TYPE, element);
        Player player = PlayerController.getInstance().getCurrentPlayer();

        FinishPane.show(player);
    }
    public Player getPlayer(){
        return PlayerController.getInstance().getCurrentPlayer();
    }
}
