package files;

import files.DB.DBController;
import files.Panes.*;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        Scene scene = new Scene(GameInitializer.getGameRoot());

        stage.setScene(scene);
        stage.setTitle("Энергоквест");

        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        System.out.println(DBController.getInstance().getAllQuestions());

    }
}