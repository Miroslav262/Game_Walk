package files.Panes.PlayerRegPaneComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class PlayerInputComponent extends HBox {
    private String playerName;
    private Color color;

    public PlayerInputComponent(){
        this.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(5), Insets.EMPTY)
        ));
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        TextField textField = new TextField();
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playerName = textField.getText();
            }
        });

        textField.setPromptText("Введите имя игрока...");
        textField.setFont(Font.font("Comic Sans MS", 20));

        this.getChildren().add(textField);

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                color = colorPicker.getValue();
            }
        });

        this.getChildren().add(colorPicker);

        Button delButton = new Button();

        delButton.setPadding(new Insets(3));
        delButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(4), new Insets(0))));
        delButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1), new Insets(0))));

        javafx.scene.image.Image delButImage = new Image("/images/Trash_Can.png");
        ImageView view = new ImageView(delButImage);

        view.setPreserveRatio(true);
        view.setFitHeight(24);

        delButton.setGraphic(view);
        delButton.setOnAction(e -> {
            ((Pane) getParent()).getChildren().remove(this);
        });

        this.getChildren().add(delButton);
    }

    public String getPlayerName() {
        return playerName;
    }

    public Color getColor() {
        return color;
    }
}
