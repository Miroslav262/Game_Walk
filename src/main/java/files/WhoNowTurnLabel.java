package files;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WhoNowTurnLabel extends StackPane {
    private static WhoNowTurnLabel instance = new WhoNowTurnLabel();

    private Label label;
    public WhoNowTurnLabel(){
        label = new Label("Сейчас ходит "+ PlayerController.getInstance().getCurrentPlayer().getName());
        label.setFont(Font.font("Comic Sans MS", 17));

        this.setBackground(new Background( new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), Insets.EMPTY)));
        this.getChildren().add(label);
    }

    public static void updateLabel(){
        instance.label.setText("Сейчас ходит " + PlayerController.getInstance().getCurrentPlayer().getName());
    }

    public static WhoNowTurnLabel getInstance(){
        return instance;
    }


}
