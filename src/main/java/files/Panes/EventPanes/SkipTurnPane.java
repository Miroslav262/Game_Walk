package files.Panes.EventPanes;

import files.Panes.BlockerPane;
import files.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;


public class SkipTurnPane extends StackPane {

    private static final SkipTurnPane instance = new SkipTurnPane();

    private final Label label;
    private final VBox modalPane;

    private SkipTurnPane() {

        modalPane = new VBox(10);
        modalPane.setBackground(new Background(new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), new Insets(0))));

        modalPane.setVisible(true);
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        modalPane.setAlignment(Pos.CENTER);



        label = new Label("Игрок ??? пропускает ход");
        label.setFont(Font.font("Comic Sans MS", 20));

        Button button = new Button("Продолжить");
        button.setBackground(new Background(new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), new Insets(0))));
        button.setFont(Font.font("Comic Sans MS", 20));
        button.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));


        button.setOnAction(e -> {
            hide();
            BlockerPane.setVisibleState(false);
        });

        Image image = new Image("/images/SkipNextTurn.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.25);

        modalPane.getChildren().addAll(label,imageView,button);
        this.getChildren().add(modalPane);
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

    public static void hide() {
        instance.setVisible(false);
    }
}
