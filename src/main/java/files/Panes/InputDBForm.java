package files.Panes;

import files.DB.DBController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    public InputDBForm(DBController dbController) {
        this.dbController = dbController;

        textQuestion = null;
        complexity = -1;

        VBox sMain = new VBox();
        sMain.setBackground(new Background(
                new BackgroundFill(Color.rgb(20, 51, 6), new CornerRadii(5), Insets.EMPTY)
        ));
        sMain.setSpacing(5);
        sMain.setPadding(new Insets(10));

        main = new VBox();
        main.setBackground(new Background(
                new BackgroundFill(Color.DARKGREEN, new CornerRadii(5), Insets.EMPTY)
        ));

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

        inputQuestion.textProperty().addListener((obs, oldVal, newVal) -> {
            this.textQuestion = newVal;
        });

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("простой", "средний", "сложный");
        comboBox.setPromptText("Выберите сложность");

        comboBox.setOnAction(e -> {
            String selected = comboBox.getValue();
            if (selected != null) {
                switch (selected) {
                    case "простой" -> complexity = 3;
                    case "средний" -> complexity = 2;
                    case "сложный" -> complexity = 1;
                    default -> complexity = -1;
                }
            }
        });

        HBox inputQBar = new HBox(7);
        inputQBar.setPadding(new Insets(10));
        inputQBar.getChildren().addAll(inputQuestion, comboBox);

        main.getChildren().add(inputQBar);

        // Добавляем 3 варианта по умолчанию
        for (int i = 0; i < 3; i++) {
            main.getChildren().add(new QuestionOptionPane());
        }

        Button newOption = new Button("Создать новый вариант");
        newOption.setOnAction(e -> {
            main.getChildren().add(main.getChildren().size() - 1, new QuestionOptionPane());
        });

        main.getChildren().add(new StackPane(newOption));

        main.setPadding(new Insets(5));
        main.setSpacing(5);

        Button submitBtn = new Button("Сохранить данные");
        submitBtn.setOnAction(e -> {
            try {
                Question q = getQuestion();
                dbController.addQuestion(q);
                new Alert(Alert.AlertType.INFORMATION, "Данные успешно добавлены").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        Button clearBtn = new Button("Очистить форму");
        clearBtn.setOnAction(e -> clear());

        HBox buttonsBar = new HBox(20);
        buttonsBar.setAlignment(Pos.CENTER);
        buttonsBar.getChildren().addAll(
                new StackPane(submitBtn),
                new StackPane(clearBtn)
        );

        sMain.getChildren().addAll(new StackPane(main), buttonsBar);

        getChildren().add(sMain);
    }

    private void clear() {
        inputQuestion.setText("");
        comboBox.setValue(null);

        for (var node : main.getChildren()) {
            if (node instanceof QuestionOptionPane qp) {
                qp.clear();
            }
        }
    }

    private Question getQuestion() {
        List<String> answers = new ArrayList<>();
        int correctID = -1;

        int optionIndex = 0;

        for (var node : main.getChildren()) {
            if (node instanceof QuestionOptionPane qp) {

                String optionText = qp.getOptionText();
                if (optionText == null || optionText.isEmpty()) {
                    throw new RuntimeException("Не все поля заполнены!");
                }

                answers.add(optionText);

                if (qp.isCorrect()) {
                    if (correctID != -1) {
                        throw new RuntimeException("Только 1 вариант ответа должен быть правильным!");
                    }
                    correctID = optionIndex;
                }

                optionIndex++;
            }
        }

        if (correctID == -1) {
            throw new RuntimeException("Выберите правильный вариант ответа!");
        }

        if (complexity == -1) {
            throw new RuntimeException("Выберите сложность вопроса!");
        }

        if (answers.size() < 2) {
            throw new RuntimeException("Должно быть не менее двух вариантов ответов!");
        }

        return new Question(textQuestion, answers, correctID, complexity);
    }
}
