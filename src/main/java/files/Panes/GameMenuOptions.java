package files.Panes;

import files.GameDrawer;
import files.GameInitializer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class GameMenuOptions extends StackPane {
    private static GameMenuOptions instance = new GameMenuOptions();
    private static boolean isVisible = false;

    private final VBox modalPane;

    private GameMenuOptions(){
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

        StyledButton exitBut = new StyledButton("Выйти из игры", 20, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        exitBut.setPrefWidth(Screen.getPrimary().getVisualBounds().getHeight()*0.3);

        modalPane.getChildren().addAll(continueBut, exitBut);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public static GameMenuOptions getInstance() {
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
