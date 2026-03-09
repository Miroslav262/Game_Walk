package files.Panes.FromEventPanes;

import files.Panes.BlockerPane;
import files.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SkipTurnPane extends StackPane {

    private static final SkipTurnPane instance = new SkipTurnPane();

    private final Label label;
    private final VBox modalPane;

    private SkipTurnPane() {

        modalPane = new VBox(10);
        modalPane.setStyle("-fx-background-color: white; -fx-padding: 20;");
        modalPane.setVisible(true);
        modalPane.setMaxHeight(100);
        modalPane.setMaxWidth(300);

        label = new Label("Игрок ??? пропускает ход");
        Button button = new Button("Продолжить");

        button.setOnAction(e -> {
            setInvisible();
            BlockerPane.setVisibleState(false);
        });

        modalPane.getChildren().addAll(new StackPane(label),new StackPane(button));
        this.getChildren().add(new StackPane (modalPane));
        this.setVisible(false);
    }

    public static SkipTurnPane getInstance() {
        return instance;
    }

    public static void show(Player player) {
        instance.label.setText("Игрок " + player.getName() + " пропускает ход");
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public static void setInvisible() {
        instance.setVisible(false);
    }
}
