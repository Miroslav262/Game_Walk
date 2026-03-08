package files.Events;

import files.WayElements.WayElement;
import javafx.event.EventType;

public class StepEvent extends WayElementEvent {

    public static final EventType<StepEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "STEP_EVENT");

    private final int steps;

    public StepEvent(WayElement element, int steps) {
        super(TYPE, element);
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }
}
