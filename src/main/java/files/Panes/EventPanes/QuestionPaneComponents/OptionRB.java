package files.Panes.EventPanes.QuestionPaneComponents;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OptionRB extends HBox {

    private final Label labelNode;
    private final RadioButton radioButton;

    public OptionRB(String label){
        super(5);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setFillHeight(true);

        this.setBackground(new Background(
                new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), Insets.EMPTY)
        ));
        this.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));

        radioButton = new RadioButton();

        labelNode = new Label(label);
        labelNode.setWrapText(true);
        labelNode.setFont(Font.font("Comic Sans MS", 20));

        labelNode.setMaxWidth(Double.MAX_VALUE);
        labelNode.setMinHeight(Region.USE_PREF_SIZE);
        labelNode.setPrefHeight(Region.USE_COMPUTED_SIZE);

        labelNode.setBackground(new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)
        ));
        labelNode.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));

        HBox.setHgrow(labelNode, Priority.ALWAYS);

        this.getChildren().addAll(radioButton, labelNode);
    }

    public void setNewState(boolean isCorrect){
        labelNode.setBackground(new Background(
                new BackgroundFill(isCorrect ? Color.GREEN : Color.RED, new CornerRadii(5), Insets.EMPTY)
        ));
        radioButton.setDisable(true);
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}
