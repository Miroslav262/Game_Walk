package files.Panes;

import files.GameDrawer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class MainMenuOptions extends StackPane {
    private static MainMenuOptions instance;
    private static boolean isVisible = false;

    private final VBox modalPane;

    private final Pane gameRoot;

    public static void initMainMenuOptions(Pane gameRoot){
        instance = new MainMenuOptions(gameRoot);
    }

    private MainMenuOptions(Pane gameRoot){
        this.gameRoot = gameRoot;

        modalPane = new VBox();
        modalPane.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), Insets.EMPTY)
        ));
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.4);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.4);
        modalPane.setAlignment(Pos.CENTER);
        modalPane.setPadding(new Insets(15));
        modalPane.setSpacing(20);

        Label label = new Label("Настройки");
        label.setFont(Font.font("Comic Sans MS", 50));
        modalPane.getChildren().add(label);

        StyledButton continueBut = new StyledButton("Продолжить", 20, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                hide();
                BlockerPane.setVisibleState(false);
                GameDrawer.getInstance().draw();
            }
        });
        continueBut.setPrefWidth(Screen.getPrimary().getVisualBounds().getHeight()*0.3);


        StyledButton restartBut = new StyledButton("Перезапустить игру", 20, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /* заглушка */
            }
        });
        restartBut.setPrefWidth(Screen.getPrimary().getVisualBounds().getHeight()*0.3);

        StyledButton exitBut = new StyledButton("Выйти в главоное меню", 20, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /* заглушка */
            }
        });
        exitBut.setPrefWidth(Screen.getPrimary().getVisualBounds().getHeight()*0.3);

        modalPane.getChildren().addAll(continueBut, restartBut, exitBut);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public static MainMenuOptions getInstance() {
        return instance;
    }

    public static boolean isItVisible(){
        return isVisible;
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
