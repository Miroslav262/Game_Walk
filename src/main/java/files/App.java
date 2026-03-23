package files;

import files.DB.DBController;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
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