package files.Panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class QuestionOptionPane extends Pane {

    private boolean isCorrect;
    private String optionText;
    private TextArea textArea;
    private CheckBox isCorrectCheckBox;

    public QuestionOptionPane(){

        isCorrect = false;
        optionText = null;

        textArea = new TextArea();
        textArea.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(4), new Insets(0))));
        textArea.setEditable(true);
        textArea.setPromptText("Введите вариант ответа...");
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(100);
        textArea.setWrapText(true);
        textArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1), new Insets(0))));
        textArea.textProperty().addListener((obs, oldVal, newVal) -> {
            this.optionText = newVal;
            System.out.println(optionText);
        });

        StackPane stackPaneCheckBox = new StackPane();

        isCorrectCheckBox = new CheckBox();
        isCorrectCheckBox.setSelected(false);
        isCorrectCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            this.isCorrect = newVal;
            System.out.println(isCorrect);
        });


        stackPaneCheckBox.getChildren().add(isCorrectCheckBox);

        Button delButton = new Button();

        delButton.setPadding(new Insets(3));
        delButton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(4), new Insets(0))));
        delButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1), new Insets(0))));

        Image delButImage = new Image("/images/Trash_Can.png");
        ImageView view = new ImageView(delButImage);

        view.setPreserveRatio(true);
        view.setFitHeight(24);

        delButton.setGraphic(view);
        delButton.setOnAction(e -> {
            ((Pane) getParent()).getChildren().remove(this);
            System.out.println("deleted");
        });


        StackPane stackPaneDelBut = new StackPane();
        stackPaneDelBut.getChildren().add(delButton);


        HBox hb = new HBox();

        hb.getChildren().add(textArea);
        hb.getChildren().add(stackPaneCheckBox);
        hb.getChildren().add(stackPaneDelBut);

        getChildren().add(hb);

        hb.setSpacing(10);
        hb.setPadding(new Insets(10));
        hb.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(7), new Insets(0))));
    }
    public void clear(){
        textArea.clear();
        isCorrectCheckBox.setSelected(false);
        optionText = null;
        isCorrect = false;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getOptionText() {
        return optionText;
    }
}