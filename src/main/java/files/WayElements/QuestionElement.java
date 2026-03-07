package files.WayElements;

import files.Question;

public class QuestionElement implements WayElement{

    private Question question;

    public QuestionElement(Question question){
        this.question = question;
    }
    @Override
    public void action() {
        //вызов формочки с заданием вопроса
    }
}
