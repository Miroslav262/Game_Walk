package files;

import javafx.scene.layout.Pane;
import main.java.DB.DBController;
import files.WayElements.WayElement;

import java.util.List;

public class Game extends Pane {
    private DBController dbController;
    private List<WayElement> elements;
    private PlayerController playerController;
    private QuestionsController questionsController;

    public Game(DBController dbController, PlayerController playerController){
        this.dbController = dbController;
        questionsController = new QuestionsController(dbController);
    }
}
