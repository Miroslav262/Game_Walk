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
    private final TextField textField;
    private final ColorPicker colorPicker;

    public PlayerInputComponent() {
        this.setBackground(new Background(
                new BackgroundFill(Color.web("#008A00"), new CornerRadii(5), Insets.EMPTY)
        ));
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        textField = new TextField();
        textField.setPromptText("Введите имя игрока...");
        textField.setFont(Font.font("Comic Sans MS", 20));
        this.getChildren().add(textField);

        colorPicker = new ColorPicker();
        this.getChildren().add(colorPicker);

        Button delButton = new Button();
        delButton.setPadding(new Insets(3));
        delButton.setBackground(new Background(
                new BackgroundFill(Color.ALICEBLUE, new CornerRadii(4), Insets.EMPTY)
        ));
        delButton.setBorder(new Border(new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1), new Insets(0)
        )));

        Image delButImage = new Image("/images/Trash_Can.png");
        ImageView view = new ImageView(delButImage);
        view.setPreserveRatio(true);
        view.setFitHeight(24);
        delButton.setGraphic(view);

        delButton.setOnAction(e -> ((Pane) getParent()).getChildren().remove(this));

        this.getChildren().add(delButton);
    }

    public String getPlayerName() {
        return textField.getText();
    }

    public Color getColor() {
        return colorPicker.getValue();
    }
}

