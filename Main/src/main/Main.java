package main;

import main.DB.DBController;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBController dbController = new DBController("jdbc:sqlite:Main/src/main/DB/app.db");

/*
        dbController.addQuestion(new main.Question("А?", Arrays.asList(new String[]{"1", "2","3"}), 0, 3));
        dbController.addQuestion(new main.Question("Б?", Arrays.asList(new String[]{"1", "2","3"}), 1,1));
        dbController.addQuestion(new main.Question("В?", Arrays.asList(new String[]{"1", "2","3"}), 2,2));
*/
       // dbController.clearDB();

        List<Question> arr = dbController.getAllQuestions();
        if(arr != null)
        for(Question question : arr){
            System.out.println(question.toString() + '\n');
        }
        System.out.println("-------------------------");
        Question q = dbController.getByID(3);
        if(q != null){
            System.out.println(q.toString());
        }
        else{
            System.out.println("нет такого вопроса");
        }

    }
}