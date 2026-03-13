package files.Panes.EventPanes;

import files.Events.StepEvent;
import files.Panes.BlockerPane;
import files.Panes.EventPanes.QuestionPaneComponents.QuestionView;
import files.Player;
import files.PlayerController;
import files.Question;
import files.QuestionsController;
import files.WayElements.Way;
import files.WayElements.WayElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class QuestionPane extends StackPane {

    private static QuestionPane instance;

    private final Label label;
    private final VBox modalPane;
    private final Question question;
    private QuestionView questionView;
    private boolean isChecked;
    private Pane gameRoot;

    private QuestionPane(Pane gameRoot) {
        this.gameRoot = gameRoot;
        question = QuestionsController.getNext();
        isChecked = false;
        modalPane = new VBox();
        modalPane.setBackground(new Background(new BackgroundFill(Color.web("#008A00"), new CornerRadii(10), new Insets(0))));

        modalPane.setVisible(true);
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth()*0.5);
        modalPane.setAlignment(Pos.CENTER);
        modalPane.setSpacing(5);
        modalPane.setPadding(new Insets(5));


        label = new Label("Игрок ??? отвечает на вопрос");
        label.setFont(Font.font("Comic Sans MS", 20));

        Button button = new Button("Ответить");
        button.setBackground(new Background(new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), new Insets(0))));
        button.setFont(Font.font("Comic Sans MS", 20));
        button.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));


        button.setOnAction(e -> {
            if(!isChecked){
                isChecked = true;
                button.setText("Продолжить");
                for(int i = 0; i<questionView.getOptionRBList().size(); i++){
                    questionView.getOptionRBList().get(i).setNewState(question.getCorrectID() == i+1);
                }
            }
            else{
                hide();
                BlockerPane.setVisibleState(false);
                if( questionView.getToggleGroup().getSelectedToggle().equals(
                        questionView.getOptionRBList().get(question.getCorrectID()-1).getRadioButton())){
                    label.setText("Игрок " + PlayerController.getInstance().getCurrentPlayer().getName() + " ответил правильно!");
                }
                else{
                    gameRoot.fireEvent(new StepEvent(Way.getInstance()
                            .getElements()
                            .get(PlayerController.getInstance().getCurrentPlayer().getPosition()),
                            (-1)*question.getComplexity()));
                }
                QuestionPane.getUpdatedInstance(gameRoot);

            }

        });

        Image image = new Image("/images/Question.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight()*0.25);

        questionView = new QuestionView(question);

        modalPane.getChildren().addAll(label,imageView,questionView,button);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }


    public static QuestionPane getUpdatedInstance(Pane gameRoot){
        instance = new QuestionPane(gameRoot);
        return  instance;
    }

    public static void show(Player player) {
        instance.label.setText("Игрок " + player.getName() + " отвечает на вопрос");
        BlockerPane.setVisibleState(true);
        instance.setVisible(true);
        instance.toFront();
    }

    public static void hide() {
        instance.setVisible(false);
    }
}
