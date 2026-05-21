package files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFormatter {

    public static void writeQuestionsToTxt(List<Question> questions, String filename) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            sb.append("Вопрос ").append(i + 1).append(":\n");
            sb.append("Текст: ").append(q.getQuestionText()).append("\n");
            sb.append("Ответы:\n");

            List<String> answers = q.getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                sb.append("  ").append(j + 1).append(") ").append(answers.get(j)).append("\n");
            }

            sb.append("Правильный ответ: ").append(q.getCorrectID() + 1).append("\n");
            sb.append("Сложность: ").append(q.getComplexity()).append("\n");
            sb.append("-------------------------------------\n\n");
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
