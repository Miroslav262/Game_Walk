package files;

import files.Panes.*;
import files.WayElements.Way;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.util.Arrays;

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
/*
        PlayerController.createInstance(Arrays.asList(new Player[]{
                new Player("Вася", Color.AQUA),
                new Player("Петя", Color.RED),
                new Player("Гриша", Color.BEIGE)
        }));
*/



    }
}