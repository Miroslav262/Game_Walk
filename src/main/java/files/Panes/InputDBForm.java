package files.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.java.DB.DBController;
import files.Question;

import java.util.ArrayList;
import java.util.List;


public class InputDBForm extends Pane {
    private DBController dbController;
    private String textQuestion;
    private TextArea inputQuestion;
    private ComboBox<String> comboBox;
    private VBox main;
    private int complexity;

    public InputDBForm(DBController dbController){
        this.dbController = dbController;

        textQuestion = null;
        complexity = -1;

        VBox sMain = new VBox();
        sMain.setBackground(new Background(new BackgroundFill(new Color(20.0/255, 51.0/255, 6.0/255, 1),  new CornerRadii(5), new Insets(0))));
        sMain.setSpacing(5);
        sMain.setPadding(new Insets(10));

        main = new VBox();


        main.setBackground(new Background(new BackgroundFill(Color.DARKGREEN,  new CornerRadii(5), new Insets(0))));
        Label nameLabel = new Label("Добавление нового вопроса");
        nameLabel.setFont(new Font(15));
        nameLabel.setTextFill(Color.GREEN);
        nameLabel.setAlignment(Pos.CENTER);


        sMain.getChildren().add(new StackPane(nameLabel));

        inputQuestion = new TextArea();
        inputQuestion.setPromptText("Введите вопрос...");


        inputQuestion.setPrefWidth(300);
        inputQuestion.setPrefHeight(100);
        inputQuestion.setWrapText(true);

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("простой", "средний", "сложный");

        comboBox.setPromptText("Выберите сложность");

        comboBox.setOnAction(e -> {
            String selected = comboBox.getValue();
            if(selected != null){
                switch (selected){
                    case "простой":
                        complexity = 3;
                        break;
                    case "средний":
                        complexity = 2;
                        break;
                    case "сложный":
                        complexity = 1;
                        break;
                    default:
                        complexity = -1;
                }
                System.out.println("Вы выбрали: " + selected);
            }

        });



        inputQuestion.textProperty().addListener((obs, oldVal, newVal) -> {
            this.textQuestion = newVal;
            System.out.println(textQuestion);
        });

        HBox inputQBar = new HBox();
        inputQBar.setSpacing(7);
        inputQBar.setPadding(new Insets(10));
        inputQBar.getChildren().add(inputQuestion);
        inputQBar.getChildren().add(comboBox);

        main.getChildren().add(inputQBar);

        for (int i = 0; i < 3; i++) {
            QuestionOptionPane qp = new QuestionOptionPane();
            main.getChildren().add(qp);
        }



        Button newOption = new Button("Создать новый вариант");
        newOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.getChildren().add(main.getChildren().size()-1,new QuestionOptionPane());
                System.out.println("Создан вариант");
            }
        });
        main.getChildren().add(new StackPane(newOption));

        main.setPadding(new Insets(5));
        main.setSpacing(5);

        Button submitBtn = new Button("Сохранить данные");
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Question q;
                try {
                    q = getQuestion();
                    System.out.println(q);

                    dbController.addQuestion(q);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Данные успешно добавлены");
                    alert.showAndWait();
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();

                }



            }
        });

        Button clearBtn = new Button("Очистить форму");
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               clear();
            }
        });

        sMain.getChildren().add(new StackPane(main));

        HBox buttonsBar = new HBox();
        buttonsBar.setAlignment(Pos.CENTER);
        buttonsBar.setSpacing(20);

        buttonsBar.getChildren().add(new StackPane(submitBtn));
        buttonsBar.getChildren().add(new StackPane(clearBtn));

        sMain.getChildren().add(buttonsBar);

        getChildren().add(sMain);
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    private void clear(){
        inputQuestion.textProperty().set("");
        for(int i = 0; i<main.getChildren().size();i++){
            if(main.getChildren().get(i) instanceof QuestionOptionPane){
                ((QuestionOptionPane)(main.getChildren().get(i))).clear();
            }
        }
        comboBox.setValue(null);

    }

    private Question getQuestion(){
        List<String> answers = new ArrayList<>();
        int correctID = -1;
        for(int i = 0; i<main.getChildren().size();i++){
            if(main.getChildren().get(i) instanceof QuestionOptionPane){
                String optionText =((QuestionOptionPane)(main.getChildren().get(i))).getOptionText();
                if(optionText != null){
                    answers.add(optionText);
                }
                else{
                    throw new RuntimeException("Не все поля заполнены!");
                }

                if(((QuestionOptionPane)(main.getChildren().get(i))).isCorrect()){
                    if(correctID != -1){
                        throw new RuntimeException("Только 1 вариант ответа правильный!");
                    }
                    else{
                        correctID = i;
                    }
                }
            }
        }
        if(correctID == -1){
            throw new RuntimeException("Должен быть выбран 1 правильный вариант ответа!");
        }
        if(complexity == -1){
            throw new RuntimeException("Выберите сложность вопроса!");
        }
        if(answers.size() <2){
            throw new RuntimeException("Должно быть не менее двух вариантов ответов!");
        }
        return new Question(this.textQuestion, answers, correctID, complexity);
    }
}
