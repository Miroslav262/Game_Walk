package files.Events;

import javafx.event.Event;
import javafx.event.EventType;
import files.WayElements.WayElement;

public abstract class WayElementEvent extends Event {

    public static final EventType<WayElementEvent> ANY = new EventType<>(Event.ANY, "WAY_ELEMENT_EVENT");

    private final WayElement element;

    public WayElementEvent(EventType<? extends WayElementEvent> type, WayElement element) {
        super(type);
        this.element = element;
    }

    public WayElement getElement() {
        return element;
    }
}
