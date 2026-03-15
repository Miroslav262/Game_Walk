package files.Panes;

import files.Player;
import files.PlayerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class PlayerInfo extends StackPane {

    public PlayerInfo(){
        VBox vBox = new VBox(5);
        vBox.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), Insets.EMPTY)
        ));
        //vBox.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.25);
        vBox.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(15);


        Label label = new Label("Игроки");
        label.setFont(Font.font("Comic Sans MS", 30));
        vBox.getChildren().add(label);

        for(Player player : PlayerController.getInstance().getPlayers()){
            HBox hBox = new HBox();
            hBox.setSpacing(20);


            Circle circle = new Circle();
            circle.setRadius(10);
            circle.setFill(player.getColor());
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(3);
            hBox.getChildren().add(new StackPane(circle));

            Label label1 = new Label(player.getName());
            label1.setFont(Font.font("Comic Sans MS", 20));
            hBox.getChildren().add(label1);

            vBox.getChildren().add(hBox);
        }
        this.getChildren().add(vBox);
    }
}
