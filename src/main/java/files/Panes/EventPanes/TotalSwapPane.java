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

public class TotalSwapPane extends StackPane {
    private static TotalSwapPane instance= new TotalSwapPane();

    private final VBox modalPane;
    private Label label;
    private TotalSwapPane(){

        modalPane = new VBox(10);
        modalPane.setBackground(new Background(new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), new Insets(0))));

        modalPane.setVisible(true);
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        modalPane.setAlignment(Pos.CENTER);

        label = new Label("Все игроки случайно меняются местами");
        label.setFont(Font.font("Comic Sans MS", 20));

        Image image = new Image("/images/TotalSwap.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.25);

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

        modalPane.getChildren().addAll(label,imageView,button);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public static TotalSwapPane getInstance(){
        return instance;
    }

    public static void show() {
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public void hide(){
        instance.setVisible(false);
    }

}
