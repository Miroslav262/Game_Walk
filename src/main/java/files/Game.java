package files;

import files.Panes.*;
import files.WayElements.Way;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Game extends StackPane {
    private static Game instance;
    private static boolean isVisible = false;


    public static Game getInstance(){
        return instance;
    }

    public static void createNewGame(){
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






        GameInitializer.getGameRoot().getChildren().add(hbox);





        Way.createNewWay(hbox);

        GameDrawer.getInstance().getGraphicsContext2D().getCanvas().widthProperty().bind(Main.getPrimaryStage().heightProperty());
        GameDrawer.getInstance().getGraphicsContext2D().getCanvas().heightProperty().bind(Main.getPrimaryStage().heightProperty());

        hbox.getChildren().add(GameDrawer.getInstance().getGraphicsContext2D().getCanvas());

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
                GameMenuOptions.show();
            }
        }));
        boxForSettingsBut.setAlignment(Pos.CENTER_RIGHT);



        vBox.getChildren().addAll(boxForSettingsBut, new PlayerInfo(), new StyledButton("Бросить кубик", 20, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int roll = Dice.getInstance().roll();
                System.out.println(roll);
                Way.getInstance().doStep(roll);
            }
        }));

        hbox.getChildren().add(vBox);
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
