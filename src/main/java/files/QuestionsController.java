package files;

import main.java.DB.DBController;

import java.util.Collections;
import java.util.List;

public class QuestionsController {
    private List<Question> questions;

    private static QuestionsController instance = new QuestionsController();

    public static QuestionsController getInstance(){
        return instance;
    }

    private QuestionsController(){
        getQuestions();
    }

    private void getQuestions(){
        questions = DBController.getInstance().getAllQuestions();
        Collections.shuffle(questions);
    }

    public static Question getNext(){
        if(instance.questions.isEmpty()){
            instance.getQuestions();
        }
        return instance.questions.removeFirst();
    }
}
