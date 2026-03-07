package main;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.DB.DBController;
import main.Panes.InputDBForm;
import main.Panes.QuestionOptionPane;
import main.Panes.QuestionWatchingPane;
import main.Panes.WatchingDB;

import java.util.List;

public class HelloFX extends Application {
    private Point2D fromCordsToPixels(Point2D cords, double xMax, double yMax, double scale) {
        double x = cords.getX()*scale;
        double y = cords.getY()*scale;

        double px = x + xMax/2.0;
        double py = yMax/2.0 - y;

        return new Point2D(px, py);
    }

    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox();

        DBController dbController = new DBController("jdbc:sqlite:Main/src/main/DB/app.db");



        vbox.setSpacing(50);
        vbox.setPadding(new Insets(15));
        vbox.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(20), new Insets(5))));



        Button getDB = new Button("Получить");
        getDB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Question> list = dbController.getAllQuestions();
                if(!list.isEmpty()){
                    for(Question q : list){
                        System.out.println(q);
                    }
                    System.out.println("-----------------------");
                }
                else{
                    System.out.println("Пустая БД");
                }
            }
        });
        vbox.getChildren().add(new StackPane(getDB));
        //vbox.getChildren().add(new WatchingDB(dbController));
        InputDBForm inputDBForm = new InputDBForm(dbController);

        HBox dbWorker = new HBox();
        dbWorker.setMaxHeight(1000);
        dbWorker.setAlignment(Pos.CENTER);
        dbWorker.getChildren().add(inputDBForm);
        dbWorker.getChildren().add(new WatchingDB(dbController));
        dbWorker.setSpacing(5);
        dbWorker.setPadding(new Insets(5));
        dbWorker.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, new CornerRadii(20), new Insets(5))));

        vbox.getChildren().add(dbWorker);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.show();
        stage.setMaximized(true);


    }
}