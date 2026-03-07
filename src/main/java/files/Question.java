package files;

import java.util.*;

public class Question {
    private String questionText;
    private List<String> answers;
    private int correctID;
    private int complexity;

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectID() {
        return correctID;
    }

    public int getComplexity() {
        return complexity;
    }

    public Question(String questionText, List<String> answers, int correctID, int complexity) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctID = correctID;
        this.complexity = complexity;
    }

    @Override
    public String toString() {
        return "main.Question{" +
                "questionText='" + questionText + '\'' +
                ", answers=" + answers +
                ", correctID=" + correctID +
                ", complexity=" + complexity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return correctID == question.correctID && complexity == question.complexity && Objects.equals(questionText, question.questionText) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, answers, correctID, complexity);
    }
}
