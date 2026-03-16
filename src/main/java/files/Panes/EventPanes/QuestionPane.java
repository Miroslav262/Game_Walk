package files.Panes.EventPanes;

import files.*;
import files.Events.StepEvent;
import files.Panes.BlockerPane;
import files.Panes.EventPanes.QuestionPaneComponents.QuestionView;
import files.WayElements.Way;
import files.WayElements.WayElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class QuestionPane extends StackPane {

    private static QuestionPane instance = null;

    private final VBox modalPane;
    private final Label label;
    private Question question;
    private QuestionView questionView;
    private boolean isChecked;
    private final Pane gameRoot;
    private Player questionPlayer;

    private QuestionPane(Pane gameRoot) {
        this.gameRoot = gameRoot;

        modalPane = new VBox(5);
        modalPane.setBackground(new Background(
                new BackgroundFill(Utils.myGreenColor, new CornerRadii(10), Insets.EMPTY)
        ));
        modalPane.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.5);
        modalPane.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth() * 0.5);
        modalPane.setAlignment(Pos.CENTER);
        modalPane.setPadding(new Insets(5));

        label = new Label("Игрок ??? отвечает на вопрос");
        label.setFont(Font.font("Comic Sans MS", 20));

        Button button = new Button("Ответить");
        button.setBackground(new Background(
                new BackgroundFill(Color.web("#2CA90B"), new CornerRadii(5), Insets.EMPTY)
        ));
        button.setFont(Font.font("Comic Sans MS", 20));
        button.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));

        button.setOnAction(e -> {
            if (!isChecked) {

                isChecked = true;
                button.setText("Продолжить");

                for (int i = 0; i < questionView.getOptionRBList().size(); i++) {
                    questionView.getOptionRBList().get(i)
                            .setNewState(question.getCorrectID() == i + 1);
                }
                if(questionView.getToggleGroup().getSelectedToggle().equals(
                        questionView.getOptionRBList().get(question.getCorrectID()-1).getRadioButton())){
                    label.setText("Игрок " + questionPlayer.getName() + " отвечает правильно");
                }
                else{
                    label.setText("Игрок " + questionPlayer.getName() + " отвечает неправильно");
                }
            } else {

                hide();
                BlockerPane.setVisibleState(false);

                boolean correct =
                        questionView.getToggleGroup().getSelectedToggle() ==
                                questionView.getOptionRBList()
                                        .get(question.getCorrectID() - 1)
                                        .getRadioButton();

                if (!correct) {
                    WayElement element = Way.getInstance()
                            .getElements()
                            .get(questionPlayer.getPosition());

                    gameRoot.fireEvent(new StepEvent(
                            element,
                            questionPlayer,
                            -question.getComplexity()
                    ));
                }

            }
        });

        // картинка
        var image = new javafx.scene.image.Image("/images/Question.png");
        var imageView = new javafx.scene.image.ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight() * 0.25);



        modalPane.getChildren().addAll(label, imageView /* questionView будет позже */, button);
        this.getChildren().add(modalPane);
        this.setVisible(false);
    }

    public static void createInstance(Pane gameRoot){
        instance = new QuestionPane(gameRoot);
    }

    public void updateInstance(){
        gameRoot.getChildren().set(gameRoot.getChildren().indexOf(instance), new QuestionPane(gameRoot));
    }

    public static QuestionPane getInstance(){
        return instance;
    }

    private void loadNewQuestion() {
        this.question = QuestionsController.getNext();
        this.isChecked = false;

        if (questionView != null) {
            modalPane.getChildren().remove(questionView);
        }

        questionView = new QuestionView(question);

        modalPane.getChildren().add(modalPane.getChildren().size() - 1, questionView);
    }


    public void show(Player player) {
        this.questionPlayer = player;
        this.loadNewQuestion();
        this.label.setText("Игрок " + player.getName() + " отвечает на вопрос");
        BlockerPane.setVisibleState(true);
        this.setVisible(true);
        this.toFront();
    }

    public void hide() {
        this.setVisible(false);
    }

}
