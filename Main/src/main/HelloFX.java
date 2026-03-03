package main;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, JavaFX!");
        label.setTextFill(Color.AQUA);
        StackPane stackPane = new StackPane();

        Circle circle = new Circle(100);
        Color color = new Color(22.0/255,53.0/255,19.0/255,1.0);
        circle.setFill(color);

        stackPane.getChildren().add(circle);
        stackPane.getChildren().add(label);

        VBox vbox = new VBox();
        vbox.getChildren().add(stackPane);
        Button button = new Button();
        button.setText("Нажми меня!");
        button.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                if(label.getText() == "Hello, JavaFX!")
                    label.setText("Кнопка была нажата!");
                else{
                    label.setText("Hello, JavaFX!");
                }
            }
        });

        StackPane stackPane1 = new StackPane();
        stackPane1.getChildren().add(button);

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.ALICEBLUE);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());


        vbox.getChildren().add(stackPane1);
        vbox.getChildren().add(new StackPane(canvas));
        vbox.setSpacing(50);
        vbox.setPadding(new Insets(15));
        vbox.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(20), new Insets(5))));



        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.show();
        stage.setMaximized(true);


    }
}