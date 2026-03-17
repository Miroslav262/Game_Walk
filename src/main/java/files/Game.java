package files;

import files.Animations.RollDiceAnimationPane;
import files.Panes.*;
import files.WayElements.Way;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class Game extends StackPane {
    private static Game instance;
    private static boolean isVisible = false;


    public static Game getInstance(){
        return instance;
    }

    private Game(){
        this.setVisible(false);

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

        getChildren().add(hbox);

        Way.createNewWay(hbox);

        GameDrawer.getInstance().getGraphicsContext2D().getCanvas().widthProperty().bind(Main.getPrimaryStage().heightProperty());
        GameDrawer.getInstance().getGraphicsContext2D().getCanvas().heightProperty().bind(Main.getPrimaryStage().heightProperty());

        hbox.getChildren().add(GameDrawer.getInstance().getGraphicsContext2D().getCanvas());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hbox.getChildren().add(spacer);


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);

        HBox boxForSettingsBut = new HBox();


        boxForSettingsBut.getChildren().add(new SettingButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameMenuOptions.show();
                BlockerPane.setVisibleState(false);
            }
        }));
        boxForSettingsBut.setAlignment(Pos.CENTER_RIGHT);


        vBox.getChildren().addAll(
                boxForSettingsBut,
                new PlayerInfo(),
                new StyledButton("Бросить кубик", 20, e -> {
                    BlockerPane.setVisibleState(true);
                    RollDiceAnimationPane.getInstance().play();

                }),
                WhoNowTurnLabel.getInstance()
        );



        hbox.getChildren().add(vBox);

        this.getChildren().add(RollDiceAnimationPane.getInstance());
        RollDiceAnimationPane.getInstance().toFront();


    }

    public static void createNewGame(){
        instance = new Game();
    }


    public static void setVisibleState(boolean state){
        isVisible = state;
        instance.setVisible(isVisible);
        instance.toFront();
    }
    public static void show(){
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();

    }

    public void hide(){
        instance.setVisible(false);
    }
}
