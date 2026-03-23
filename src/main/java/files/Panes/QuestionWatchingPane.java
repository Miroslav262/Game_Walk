package files.Panes;

import files.DB.DBController;
import files.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import files.Question;

public class QuestionWatchingPane extends Pane {
    Question question;
    public QuestionWatchingPane(Question question){
        this.question = question;

        HBox main = new HBox();
        main.setPadding(new Insets(5));

        TextArea qText = new TextArea(question.getQuestionText());
        qText.setEditable(false);
        qText.setPrefWidth(App.getPrimaryStage().getWidth()*0.2);
        qText.setPrefHeight(App.getPrimaryStage().getHeight()*0.2);
        qText.setWrapText(true);

        VBox qTextAndHardness = new VBox();
        qTextAndHardness.setSpacing(5);
        qTextAndHardness.setPadding(new Insets(5));
        qTextAndHardness.getChildren().add(
                new StackPane(
                        qText
                )
        );
        String hardness = "";
        switch (question.getComplexity()){
            case 1:
                hardness = "сложный";
                break;
            case 2:
                hardness = "средний";
                break;
            case 3:
                hardness = "лёгкий";
                break;
        }
        TextField hardnessField = new TextField(hardness);
        hardnessField.setEditable(false);

        qTextAndHardness.getChildren().add(
                new StackPane(
                        hardnessField
                )
        );


        VBox answers = new VBox();

        for(int i = 0; i< question.getAnswers().size(); i++){
            HBox answer = new HBox();
            answer.setBackground(new Background(new BackgroundFill(Color.DARKGREEN,  new CornerRadii(5), new Insets(0))));
            RadioButton rb = new RadioButton();

            rb.setMouseTransparent(true);
            rb.setFocusTraversable(false);

            if(question.getCorrectID() - 1 == i){
                rb.setSelected(true);
            }
            else{
                rb.setSelected(false);
            }
            answer.getChildren().add(rb);
            TextField label = new TextField(question.getAnswers().get(i));
            label.setEditable(false);
            answer.getChildren().add(label);
            answer.setSpacing(10);
            answer.setPadding(new Insets(5));

            answers.getChildren().add(answer);
        }
        answers.setSpacing(5);
        answers.setPadding(new Insets(5));

        main.getChildren().add(qTextAndHardness);
        main.getChildren().add(answers);
        main.setSpacing(20);
        main.setAlignment(Pos.CENTER);

        getChildren().add(main);
        main.setBackground(new Background(new BackgroundFill(new Color(20.0/255, 51.0/255, 6.0/255, 1),  new CornerRadii(5), new Insets(0))));

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

        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBController.getInstance().delQuestion(question);
                ((VBox)(main.getParent().getParent())).getChildren().remove(main.getParent());
            }
        });

        main.getChildren().add(delButton);


    }


}
