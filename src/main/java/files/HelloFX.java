package files;

import files.Panes.DBWorker;
import files.WayElements.Way;
import files.WayElements.WayElement;
import files.WayElements.WayGenerator;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.DB.DBController;


import javax.imageio.IIOException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

        DBController dbController = new DBController("jdbc:sqlite:src/main/java/files/DB/app.db");



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

        DBWorker dbWorker = new DBWorker(dbController);
        vbox.getChildren().add(dbWorker);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.show();
        stage.setMaximized(true);

        try{
            List<WayElement> result = WayGenerator.genWay("src/main/java/files/config.yaml");
            for(WayElement el :  result){
                System.out.println(el);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
        catch (InstantiationException e) {
            System.out.println(e.getMessage());
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

    }
}