package files.Panes.EventPanes.QuestionPaneComponents;

import files.Question;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

public class QuestionView extends StackPane {

    private Question question;
    private TextArea textArea;
    private List<OptionRB> optionRBList;
    private VBox vBox;
    private ToggleGroup toggleGroup;

    public QuestionView(Question question){
        this.question = question;
        textArea = new TextArea(question.getQuestionText());
        textArea.setEditable(false);
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(100);
        textArea.setWrapText(true);
        textArea.setFont(Font.font("Comic Sans MS", 20));

        vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), new Insets(0))));
        vBox.getChildren().add(textArea);

        toggleGroup = new ToggleGroup();
        optionRBList = new ArrayList<>();

        for(String str : question.getAnswers()){
            OptionRB optionRB = new OptionRB(str);
            vBox.getChildren().add(optionRB);
            optionRB.getRadioButton().setToggleGroup(toggleGroup);
            optionRBList.add(optionRB);
        }

        this.getChildren().add(vBox);
        this.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.3);
        this.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.3);
    }

    public List<OptionRB> getOptionRBList() {
        return optionRBList;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }
}
