import java.util.*;

public class Question {
    private String questionText;
    private List<String> answers;
    private int correctID;

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectID() {
        return correctID;
    }

    public Question(String questionText, List<String> answers, int correctID) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctID = correctID;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", answers=" + answers +
                ", correctID=" + correctID +
                '}';
    }
}
