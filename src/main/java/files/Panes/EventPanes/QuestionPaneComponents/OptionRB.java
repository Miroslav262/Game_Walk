package files.Panes.EventPanes.QuestionPaneComponents;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashSet;

public class OptionRB extends StackPane{
    private HBox hBox;
    private TextField textField;
    private RadioButton radioButton;

    public OptionRB(String label){
        hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), new Insets(0))));
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));

        textField = new TextField(label);
        textField.setEditable(false);
        textField.setFont(Font.font("Comic Sans MS", 20));
        textField.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(0))));

        radioButton = new RadioButton();

        hBox.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));

        textField.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));

        hBox.getChildren().addAll(new StackPane(radioButton), textField);
        this.getChildren().add(hBox);
    }

    public void setNewState(Boolean isCorrect){
        textField.setBackground(new Background(new BackgroundFill(isCorrect ? Color.GREEN : Color.RED , new CornerRadii(5), new Insets(0))));
        radioButton.setDisable(true);
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}
