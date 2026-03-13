package files.Events;

import files.Player;
import files.WayElements.WayElement;
import javafx.event.EventType;

public class StepEvent extends WayElementEvent {

    public static final EventType<StepEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "STEP_EVENT");
    private final Player player;
    private final int steps;

    public StepEvent(WayElement element, Player player, int steps) {
        super(TYPE, element);
        this.player = player;
        this.steps = steps;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSteps() {
        return steps;
    }
}
