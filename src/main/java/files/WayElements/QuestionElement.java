package files.WayElements;

import files.Question;

public class QuestionElement implements WayElement{


    public QuestionElement(){
    }

    @Override
    public String toString() {
        return "QuestionElement{}";
    }

    @Override
    public void action() {
        //вызов формочки с заданием вопроса
    }
}
