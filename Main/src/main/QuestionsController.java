package main;

import main.DB.DBController;
import java.util.Collections;
import java.util.List;

public class QuestionsController {
    private final DBController dbController;
    private List<Question> questions;

    public QuestionsController(DBController dbController){
        this.dbController = dbController;
        getQuestions();
    }

    private void getQuestions(){
        questions = dbController.getAllQuestions();
        Collections.shuffle(questions);
    }

    public Question getNext(){
        if(questions.isEmpty()){
           getQuestions();
        }
        return questions.removeFirst();
    }
}
