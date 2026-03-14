package files;

import files.Events.*;
import files.Panes.BlockerPane;
import files.Panes.EventPanes.*;
import files.WayElements.Way;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage stage) {


        StackPane sMain = new StackPane();
        Scene scene = new Scene(sMain);
        stage.setScene(scene);
        stage.setTitle("Энергоквест");
        stage.show();
        stage.setMaximized(true);

        PlayerController.createInstance(Arrays.asList(new Player[]{
                new Player("Вася", Color.AQUA),
                new Player("Петя", Color.RED),
                new Player("Гриша", Color.BEIGE)
        }));

        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.setPadding(new Insets(15));
        BackgroundImage bgImage = new BackgroundImage(
                new Image("/images/Background.jpg", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        false, false,
                        true,
                        true
                )
        );

        hbox.setBackground(new Background(bgImage));

        hbox.addEventHandler(FinishEvent.TYPE, e -> {
            FinishPane.show(e.getPlayer());
        });
        hbox.addEventHandler(SkipTurnEvent.TYPE, e -> {
            SkipTurnPane.show(e.getPlayer());
        });
        hbox.addEventHandler(StepEvent.TYPE, e -> {
            Player player = e.getPlayer();
            int steps = e.getSteps();

            int newPos = player.getPosition() + steps;

            if (newPos >= Way.getInstance().getElements().size()) {
                FinishPane.show(player);
                return;
            }

            if (newPos < 0) newPos = 0;

            player.setPosition(newPos);

            StepPane.show(player, steps);
            e.consume();
        });

        hbox.addEventHandler(TotalSwapEvent.TYPE, e -> {
            TotalSwapPane.show();
        });

        hbox.addEventHandler(SkipAnotherPlayerTurnEvent.TYPE, e -> {
            SkipAnotherPlayerTurnPane.show();

        });

        hbox.addEventHandler(QuestionEvent.TYPE, e -> {
            hbox.addEventHandler(QuestionEvent.TYPE, event -> {
                Player player = PlayerController.getInstance().getCurrentPlayer();

                QuestionPane.getInstance(hbox).show(player);

                BlockerPane.setVisibleState(true);

                event.consume();
            });

        });



        sMain.getChildren().add(hbox);
        sMain.getChildren().add(BlockerPane.getInstance());
        sMain.getChildren().add(SkipTurnPane.getInstance());
        sMain.getChildren().add(FinishPane.getInstance());
        sMain.getChildren().add(StepPane.getInstance());
        sMain.getChildren().add(TotalSwapPane.getInstance());
        sMain.getChildren().add(SkipAnotherPlayerTurnPane.getInstance());
        sMain.getChildren().add(QuestionPane.getInstance(hbox));
/*
        Way.createNewWay(hbox);
        Dice dice = new Dice();

        Button nextMove = new Button("Следующий ход");
        nextMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int roll = dice.roll();
                System.out.println(roll);
                Way.getInstance().doStep(roll);

            }
        });
        hbox.getChildren().add(nextMove);

*/
        Way.createNewWay(hbox);
        GameDrawer gameDrawer = new GameDrawer();
        gameDrawer.getGraphicsContext2D().getCanvas().widthProperty().bind(stage.heightProperty());
        gameDrawer.getGraphicsContext2D().getCanvas().heightProperty().bind(stage.heightProperty());



        hbox.getChildren().add(gameDrawer.getGraphicsContext2D().getCanvas());

        Platform.runLater(gameDrawer::draw);


    }
}