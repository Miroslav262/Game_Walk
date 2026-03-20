package files.Panes;

import files.Game;
import files.GameInitializer;
import files.Panes.PlayerRegPaneComponents.PlayerInputComponent;
import files.Player;
import files.PlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegistrationPane extends StackPane {
    private static PlayerRegistrationPane instance = new PlayerRegistrationPane();
    private static boolean isVisible = false;

    public static PlayerRegistrationPane getInstance() {
        return instance;
    }

    private PlayerRegistrationPane() {

        Image img = new Image(getClass().getResource("/images/PlayerRegMenuImg.png").toExternalForm());

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

        this.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setFillWidth(false);

        Label label = new Label("Главное меню");
        label.setFont(Font.font("Comic Sans MS", 17));
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox();
        vBox1.setBackground(new Background(
                new BackgroundFill(Color.web("#14600C"), new CornerRadii(5), Insets.EMPTY)
        ));
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(10);
        vBox1.setPadding(new Insets(10));

        StyledButton addPlayerBut = new StyledButton("Добавить игрока", 17, e -> {
            vBox1.getChildren().add(vBox1.getChildren().size() - 1, new PlayerInputComponent());
        });

        vBox1.getChildren().addAll(
                new PlayerInputComponent(),
                new PlayerInputComponent(),
                new PlayerInputComponent(),
                addPlayerBut
        );

        vBox.setPadding(new Insets(10));
        vBox.getChildren().add(vBox1);

        StyledButton submitBut = new StyledButton("Продолжить", 17, e -> {

            List<PlayerInputComponent> inputs = new ArrayList<>();

            for (Node node : vBox1.getChildren()) {
                if (node instanceof PlayerInputComponent pic) {
                    inputs.add(pic);
                }
            }

            if (inputs.size() < 2) {
                new Alert(Alert.AlertType.ERROR, "Слишком мало участников").show();
                return;
            }

            for (PlayerInputComponent current : inputs) {
                if (current.getColor() == null ||
                        current.getPlayerName() == null ||
                        current.getPlayerName().isEmpty()) {

                    new Alert(Alert.AlertType.ERROR, "Заполните пустые поля").show();
                    return;
                }
            }

            List<Player> result = new ArrayList<>();
            for (PlayerInputComponent pic : inputs) {
                result.add(new Player(pic.getPlayerName(), pic.getColor()));
            }

            PlayerController.createInstance(result);

            Game.createNewGame();
            GameInitializer.getGameRoot().getChildren().addFirst(Game.getInstance());
            Game.show();
            hide();
        });

        vBox.getChildren().add(submitBut);
        this.getChildren().add(vBox);
    }

    public static void setVisibleState(boolean state) {
        isVisible = state;
        instance.setVisible(isVisible);
        instance.toFront();
    }

    public static void show() {
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public void hide() {
        instance.setVisible(false);
    }
}
