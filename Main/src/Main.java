import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBController dbController = new DBController("jdbc:sqlite:Main/src/DB/app.db");

        dbController.addQuestion(new Question("А?", Arrays.asList(new String[]{"1", "2","3"}), 1));
        dbController.addQuestion(new Question("Б?", Arrays.asList(new String[]{"1", "2","3"}), 2));
        dbController.addQuestion(new Question("В?", Arrays.asList(new String[]{"1", "2","3"}), 3));

        List<Question> arr = dbController.getAllQuestions();
        for(Question question : arr){
            System.out.println(question.toString() + '\n');
        }
    }
}