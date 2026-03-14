package files.WayElements;

import files.Events.QuestionEvent;
import files.Events.StepEvent;
import javafx.event.Event;
import javafx.scene.image.Image;

public class QuestionElement extends WayElement {


    public QuestionElement(){
    }

    @Override
    public Image getImage() {
        return new Image("/images/Question.png");
    }

    @Override
    public Event getEvent() {
        return new QuestionEvent(this);
    }

    @Override
    public Image getMiniImage() {
        return new Image("/images/Optimazed/QuestionOptimized.png");
    }

    @Override
    public String toString() {
        return "QuestionElement{}";
    }
}
