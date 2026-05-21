package files;

import java.io.FileWriter;
import java.io.IOException;
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

    private static String escape(String s) {
        return s.replace("\"", "\\\"");
    }
    private String answersToJson() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < answers.size(); i++) {
            sb.append("\"").append(escape(answers.get(i))).append("\"");
            if (i < answers.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "{"
                + "\"questionText\":\"" + escape(questionText) + "\","
                + "\"answers\":" + answersToJson() + ","
                + "\"correctID\":" + correctID + ","
                + "\"complexity\":" + complexity
                + "}";
    }
    public static String questionsToJson(List<Question> questions) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"questions\":[");

        for (int i = 0; i < questions.size(); i++) {
            sb.append(questions.get(i).toString());
            if (i < questions.size() - 1) sb.append(",");
        }

        sb.append("]}");
        return sb.toString();
    }

    public static void writeQuestionsToTxt(List<Question> questions, String filename) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            sb.append("Вопрос №").append(i + 1).append(":\n");
            sb.append("Текст: ").append(q.getQuestionText()).append("\n");
            sb.append("Ответы:\n");

            List<String> answers = q.getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                sb.append("  ").append(j + 1).append(") ").append(answers.get(j)).append("\n");
            }

            sb.append("Правильный ответ: ").append(q.getCorrectID() + 1).append("\n");
            sb.append("Сложность: ").append(q.getComplexity() == 1 ? "сложный" :
                    q.getComplexity() == 2 ? "средний" : "лёгкий").append("\n\n");
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
