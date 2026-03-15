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

public class FinishPane extends StackPane {

    private static FinishPane instance= new FinishPane();

    private final VBox modalPane;
    private Label label;
    private FinishPane(){

        modalPane = new VBox(10);
        modalPane.setBackground(new Background(new BackgroundFill(Utils.myGreenColor, new CornerRadii(10), new Insets(0))));

        modalPane.setVisible(true);
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        modalPane.setAlignment(Pos.CENTER);

        label = new Label("Игрок ??? побеждает!");
        label.setFont(Font.font("Comic Sans MS", 20));

        Image image = new Image("/images/Finish.png");
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
            GameDrawer.getInstance().draw();
        });

        modalPane.getChildren().addAll(label,imageView,button);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public static FinishPane getInstance(){
        return instance;
    }

    public static void show(Player player) {
        instance.label.setText("Игрок " + player.getName() + " побеждает!");
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public void hide(){
        instance.setVisible(false);
    }

}
