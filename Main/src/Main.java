
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBController dbController = new DBController("jdbc:sqlite:Main/src/DB/app.db");
        dbController.clearDB();
        /*
        dbController.addQuestion(new Question("А?", Arrays.asList(new String[]{"1", "2","3"}), 0, 3));
        dbController.addQuestion(new Question("Б?", Arrays.asList(new String[]{"1", "2","3"}), 1,1));
        dbController.addQuestion(new Question("В?", Arrays.asList(new String[]{"1", "2","3"}), 2,2));
*/
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