package files.Events;

import files.Player;
import javafx.event.Event;
import javafx.event.EventType;


public class PassMotion extends Event {

    public static final EventType<PassMotion> TYPE = new EventType<>(Event.ANY, "PASS_MOTION_EVENT");

    private final Player player;

    public PassMotion(EventType<? extends PassMotion> type, Player player) {
        super(type);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}