package files.Events;

import files.Panes.EventPanes.SkipTurnPane;
import files.Player;
import files.PlayerController;
import files.WayElements.WayElement;
import javafx.event.EventType;

public class SkipTurnEvent extends WayElementEvent {

    private final Player player;

    public static final EventType<SkipTurnEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "SKIP_TURN_EVENT");

    public SkipTurnEvent(WayElement element) {
        super(TYPE, element);

        this.player = PlayerController.getInstance().getCurrentPlayer();

        player.passMation();
        System.out.println(player.toString() + "пропускает ход");
        SkipTurnPane.show(player);
    }

    public Player getPlayer() {
        return player;
    }
}

