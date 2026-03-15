package files.Panes.EventPanes;

import files.GameDrawer;
import files.Panes.BlockerPane;
import files.Player;
import files.Utils;
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

public class StepPane extends StackPane {

    private static final StepPane instance = new StepPane();

    private final Label label;
    private final VBox modalPane;
    private ImageView imageView;

    private StepPane() {

        modalPane = new VBox(10);
        modalPane.setBackground(new Background(new BackgroundFill(Utils.myGreenColor, new CornerRadii(10), new Insets(0))));

        modalPane.setVisible(true);
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        modalPane.setAlignment(Pos.CENTER);



        label = new Label("Игрок ??? переходит");
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
            GameDrawer.getInstance().draw();
        });

        imageView = new ImageView(new Image("/images/Plus3Steps.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.25);

        modalPane.getChildren().addAll(label,imageView,button);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public VBox getModalPane() {
        return modalPane;
    }

    public static void setImageView(String path){
        instance.imageView.setImage(new Image(path));
    }



    public static StepPane getInstance() {
        return instance;
    }

    public static void show(Player player, int steps) {
        String addition = " переходит на " +Math.abs( steps) + ((steps > 0) ? " вперёд" : " назад");

        if (steps < 0) {
            setImageView("/images/Minus3Steps.png");
        } else {
            setImageView("/images/Plus3Steps.png");
        }

        instance.label.setText("Игрок " + player.getName() + addition);
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public static void hide() {
        instance.setVisible(false);
    }
}
