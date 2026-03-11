package files;

import files.Events.FinishEvent;
import files.Events.SkipTurnEvent;
import files.Panes.BlockerPane;
import files.Panes.DBWorker;
import files.Panes.EventPanes.FinishPane;
import files.Panes.EventPanes.SkipTurnPane;
import files.WayElements.Finish;
import files.WayElements.Way;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.DB.DBController;


import java.util.Arrays;

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

        PlayerController.createInstance(Arrays.asList(new Player[]{
                new Player("Вася", Color.AQUA),
                new Player("Петя", Color.RED),
                new Player("Гриша", Color.BEIGE)
        }));

        VBox vbox = new VBox();
        vbox.setSpacing(50);
        vbox.setPadding(new Insets(15));
        vbox.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(20), new Insets(5))));

        DBWorker dbWorker = new DBWorker(DBController.getInstance());

        vbox.getChildren().add(dbWorker);
/*

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

*/

        vbox.addEventHandler(FinishEvent.TYPE, e -> {
            FinishPane.show(e.getPlayer());
        });
        vbox.addEventHandler(SkipTurnEvent.TYPE, e -> {
            SkipTurnPane.show(e.getPlayer());
        });

        StackPane sMain = new StackPane();
        sMain.getChildren().add(vbox);
        sMain.getChildren().add(BlockerPane.getInstance());
        sMain.getChildren().add(FinishPane.getInstance());
/*
        Way way = new Way(vbox);
        Dice dice = new Dice();
        for(int i = 0; i<20;i++){
            int roll = dice.roll();
            System.out.println(roll);
            way.doStep(roll);
        }

 */

        Scene scene = new Scene(sMain);
        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.show();
        stage.setMaximized(true);



/*
        javafx.scene.layout.VBox modalPane = new VBox(10);
        modalPane.setStyle("-fx-background-color: white; -fx-padding: 20;");
        modalPane.setVisible(true);

        Label label = new Label("Игрок ??? пропускает ход");
        Button button = new Button("Продолжить");

        button.setOnAction(e -> {
            modalPane.setVisible(false);
            BlockerPane.setVisibleState(false);
        });

        modalPane.getChildren().addAll(label, button);

        vbox.getChildren().add(new StackPane(modalPane));
        */

    }
}