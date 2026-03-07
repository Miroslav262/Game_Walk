package files.Panes;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import main.java.DB.DBController;
import files.Question;

import java.util.List;

public class WatchingDB extends Pane {

    private final DBController dbController;
    private ScrollPane scrollPane;

    public WatchingDB(DBController dbController) {
        this.dbController = dbController;

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        scrollPane = createScrollPane();
        root.getChildren().add(scrollPane);

        Button reload = new Button("Обновить данные");
        reload.setOnAction(this::reloadData);

        root.getChildren().add(new StackPane(reload));
        root.setBackground(new Background(new BackgroundFill(new Color(20.0/255, 51.0/255, 6.0/255, 1),  new CornerRadii(5), new Insets(0))));

        getChildren().add(root);
    }

    private ScrollPane createScrollPane() {
        VBox content = new VBox(5);
        content.setPadding(new Insets(5));

        List<Question> questions = dbController.getAllQuestions();
        for (Question q : questions) {
            content.getChildren().add(new QuestionWatchingPane(q));
        }

        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        sp.setPannable(true);
        sp.setPrefHeight(700);
        return sp;
    }

    private void reloadData(ActionEvent event) {
        VBox parent = (VBox) getChildren().get(0);
        parent.getChildren().remove(scrollPane);
        scrollPane = createScrollPane();
        parent.getChildren().add(0, scrollPane);
    }
}
