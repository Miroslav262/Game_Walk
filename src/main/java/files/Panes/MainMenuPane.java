package files.Panes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class MainMenuPane extends StackPane {
    private static MainMenuPane instance = new MainMenuPane();
    private static boolean isVisible = true;

    public static MainMenuPane getInstance(){
        return instance;
    }

    private MainMenuPane(){
        this.setPrefSize(
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight()
        );


        Image img = new Image(getClass().getResource("/images/MainMenuImg.png").toExternalForm());

        BackgroundImage bg = new BackgroundImage(
                img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        this.setBackground(new Background(
                bg
        ));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        this.getChildren().add(vBox);

        Label label = new Label("Энергоквест");
        label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
        label.setTextFill(Color.BLACK);
        vBox.getChildren().add(label);


        StyledButton newGameBut = new StyledButton("Новая игра", 30, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerRegistrationPane.show();
                MainMenuPane.getInstance().hide();
            }
        });
        newGameBut.setPrefWidth(Screen.getPrimary().getBounds().getWidth()*0.35);

        StyledButton changeDBBut = new StyledButton("Редактировать базу вопросов", 30, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBWorker.show();
                MainMenuPane.hide();
            }
        });
        changeDBBut.setPrefWidth(Screen.getPrimary().getBounds().getWidth()*0.35);

        StyledButton exitBut = new StyledButton("Выход", 30, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        exitBut.setPrefWidth(Screen.getPrimary().getBounds().getWidth()*0.35);

        vBox.getChildren().addAll(newGameBut, changeDBBut,exitBut);
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

    public static void hide(){
        instance.setVisible(false);
    }
}
