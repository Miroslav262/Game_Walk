package files.Panes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class MainMenuPane extends StackPane {
    private static MainMenuPane instance = new MainMenuPane();
    private static boolean isVisible = false;

    public static MainMenuPane getInstance(){
        return instance;
    }

    private MainMenuPane(){
        this.setPrefSize(
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight()
        );

        Screen.getPrimary().getBounds().getWidth();
        this.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(5), Insets.EMPTY)
        ));

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        this.getChildren().add(hBox);

        Label label = new Label("Главное меню");
        label.setFont(Font.font("Comic Sans MS", 35));
        hBox.getChildren().add(label);

        StyledButton newGameBut = new StyledButton("Новая игра", 30, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerRegistrationPane.show();
                MainMenuPane.getInstance().hide();
            }
        });

        StyledButton changeDBBut = new StyledButton("Редактировать базу вопросов", 30, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /* заглушка */
            }
        });

        StyledButton exitBut = new StyledButton("Выход", 30, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
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
