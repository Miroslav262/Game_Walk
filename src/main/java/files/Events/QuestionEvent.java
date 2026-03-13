package files.Events;

import files.WayElements.WayElement;
import javafx.event.EventType;

public class QuestionEvent extends WayElementEvent {

    public static final EventType<QuestionEvent> TYPE =
            new EventType<>(WayElementEvent.ANY, "QUESTION_EVENT");

    public QuestionEvent(WayElement element) {
        super(TYPE, element);
    }
}
