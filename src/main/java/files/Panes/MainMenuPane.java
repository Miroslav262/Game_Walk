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
    private static boolean isVisible = true;

    public static MainMenuPane getInstance(){
        return instance;
    }

    private MainMenuPane(){
        this.setPrefSize(
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight()
        );


        this.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(0), Insets.EMPTY)
        ));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        this.getChildren().add(vBox);

        Label label = new Label("Главное меню");
        label.setFont(Font.font("Comic Sans MS", 35));
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
                /* заглушка */
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

    public void hide(){
        instance.setVisible(false);
    }
}
