package files;

import files.Events.*;
import files.Panes.*;
import files.Panes.EventPanes.*;
import files.WayElements.Way;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;

import javafx.scene.control.Button;

import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage stage) {



        StackPane sMain = new StackPane();
        Scene scene = new Scene(sMain);

        stage.setScene(scene);
        stage.setTitle("Энергоквест");

        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
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
        QuestionPane.createInstance(sMain);
        /*
        ColorPicker colorPicker = new ColorPicker();

        colorPicker.setOnAction(e -> {
            Color color = colorPicker.getValue();
            System.out.println("Выбран цвет: " + color);
        });

        sMain.getChildren().add(colorPicker);
*/
        hbox.setBackground(new Background(bgImage));

        sMain.addEventHandler(FinishEvent.TYPE, e -> {
            FinishPane.show(e.getPlayer());
        });
        sMain.addEventHandler(SkipTurnEvent.TYPE, e -> {
            SkipTurnPane.show(e.getPlayer());
        });
        sMain.addEventHandler(StepEvent.TYPE, e -> {
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

        sMain.addEventHandler(TotalSwapEvent.TYPE, e -> {
            TotalSwapPane.show();
        });

        sMain.addEventHandler(SkipAnotherPlayerTurnEvent.TYPE, e -> {
            SkipAnotherPlayerTurnPane.show();

        });

        sMain.addEventHandler(QuestionEvent.TYPE, e -> {
            Player player = PlayerController.getInstance().getCurrentPlayer();

            QuestionPane.getInstance().show(player);
            BlockerPane.setVisibleState(true);

            e.consume();
        });




        sMain.getChildren().add(hbox);
        sMain.getChildren().add(BlockerPane.getInstance());
        sMain.getChildren().add(SkipTurnPane.getInstance());
        sMain.getChildren().add(FinishPane.getInstance());
        sMain.getChildren().add(StepPane.getInstance());
        sMain.getChildren().add(TotalSwapPane.getInstance());
        sMain.getChildren().add(SkipAnotherPlayerTurnPane.getInstance());
        sMain.getChildren().add(QuestionPane.getInstance());

      //  sMain.getChildren().add(Game.getInstance());
      //  sMain.getChildren().add(PlayerRegistrationPane.getInstance());
       // sMain.getChildren().add(MainMenuPane.getInstance());

        MainMenuOptions.initMainMenuOptions(hbox);
        sMain.getChildren().add(MainMenuOptions.getInstance());


        Way.createNewWay(hbox);


        GameDrawer.getInstance().getGraphicsContext2D().getCanvas().widthProperty().bind(stage.heightProperty());
        GameDrawer.getInstance().getGraphicsContext2D().getCanvas().heightProperty().bind(stage.heightProperty());

        hbox.getChildren().add(GameDrawer.getInstance().getGraphicsContext2D().getCanvas());

        Dice dice = new Dice();


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hbox.getChildren().add(spacer);


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);

        HBox boxForSettingsBut = new HBox();


        boxForSettingsBut.getChildren().add(new SettingButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenuOptions.show();
            }
        }));
        boxForSettingsBut.setAlignment(Pos.CENTER_RIGHT);



        vBox.getChildren().addAll(boxForSettingsBut, new PlayerInfo(), new StyledButton("Бросить кубик", 20, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int roll = dice.roll();
                System.out.println(roll);
                Way.getInstance().doStep(roll);
            }
        }));

        hbox.getChildren().add(vBox);


    }
}