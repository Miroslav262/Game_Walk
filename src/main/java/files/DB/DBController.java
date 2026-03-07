package main.java.DB;

import files.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {
    private Connection connection;

    public DBController(String url){
        try{
            connection = DriverManager.getConnection(url);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) {
                System.out.println("База пустая: нет таблиц");
                createDB();

            } else {
                System.out.println("В базе есть таблицы");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDB(){
        try{
            String createQuestion =
                    "CREATE TABLE IF NOT EXISTS Question (" +
                            "idQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "text TEXT NOT NULL, " +
                            "complexity INTEGER NOT NULL" +
                            ");";

            connection.prepareStatement(createQuestion).executeUpdate();

            String createOption =
                    "CREATE TABLE IF NOT EXISTS Option (" +
                            "idO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "idQ INTEGER NOT NULL, " +
                            "text TEXT NOT NULL, " +
                            "isCorrect BOOLEAN NOT NULL DEFAULT 0, " +
                            "FOREIGN KEY (idQ) REFERENCES Question(idQ)" +
                            ");";

            connection.prepareStatement(createOption).executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addQuestion(Question question) {
        try {

            String insertQuestion = "INSERT INTO Question(text, complexity) VALUES (?, ?)";
            PreparedStatement stmtQ = connection.prepareStatement(insertQuestion, Statement.RETURN_GENERATED_KEYS);
            stmtQ.setString(1, question.getQuestionText());
            stmtQ.setInt(2, question.getComplexity());
            stmtQ.executeUpdate();

            ResultSet rs = stmtQ.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("Не удалось получить id вопроса");
            }
            int idQ = rs.getInt(1);

            String insertOption = "INSERT INTO Option(idQ, text, isCorrect) VALUES (?, ?, ?)";
            PreparedStatement stmtO = connection.prepareStatement(insertOption);

            List<String> answers = question.getAnswers();
            int correctIndex = question.getCorrectID();

            for (int i = 0; i < answers.size(); i++) {
                stmtO.setInt(1, idQ);
                stmtO.setString(2, answers.get(i));
                stmtO.setBoolean(3, i == correctIndex);
                stmtO.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        try {
            String sqlQ = "SELECT idQ, text, complexity FROM Question";
            PreparedStatement stmtQ = connection.prepareStatement(sqlQ);
            ResultSet rsQ = stmtQ.executeQuery();

            while (rsQ.next()) {
                int idQ = rsQ.getInt("idQ");
                String questionText = rsQ.getString("text");
                int complexity = rsQ.getInt("complexity");

                String sqlO = "SELECT text, isCorrect FROM Option WHERE idQ = ?";
                PreparedStatement stmtO = connection.prepareStatement(sqlO);
                stmtO.setInt(1, idQ);
                ResultSet rsO = stmtO.executeQuery();

                List<String> answers = new ArrayList<>();
                int correctIndex = -1;
                int index = 0;

                while (rsO.next()) {
                    String answerText = rsO.getString("text");
                    boolean isCorrect = rsO.getBoolean("isCorrect");

                    answers.add(answerText);

                    if (isCorrect) {
                        correctIndex = index;
                    }

                    index++;
                }

                Question q = new Question(questionText, answers, correctIndex, complexity);
                questions.add(q);
            }
        }
        catch (SQLException e) {
            return null;
        }

        return questions;
    }
    public Question getByID(int idQ){
        try{
            Question result;

            String sqlQ = "SELECT text, complexity FROM Question WHERE idQ = ?";
            PreparedStatement preparedStatementQ = connection.prepareStatement(sqlQ);
            preparedStatementQ.setInt(1, idQ);
            ResultSet resultSetQ = preparedStatementQ.executeQuery();

            String questionText = resultSetQ.getString("text");
            int complexity = resultSetQ.getInt("complexity");

            if(questionText == null) throw new SQLException();

            String sqlO = "SELECT text, isCorrect FROM Option WHERE idQ = ?";
            PreparedStatement preparedStatementO = connection.prepareStatement(sqlO);
            preparedStatementO.setInt(1, idQ);
            ResultSet resultSetO = preparedStatementO.executeQuery();

            List<String> answers = new ArrayList<>();
            int correctIndex = -1;
            int index = 0;

            while(resultSetO.next()){
                String answerText = resultSetO.getString("text");
                boolean isCorrect = resultSetO.getBoolean("isCorrect");

                answers.add(answerText);

                if(isCorrect){
                    correctIndex = index;
                }
                index++;
            }
            result = new Question(questionText, answers, correctIndex, complexity);
            return result;
        }
        catch (SQLException e) {
            return null;
        }
    }
    public void clearDB() {
        try {
            String sqlDropOption = "DROP TABLE IF EXISTS Option";
            connection.prepareStatement(sqlDropOption).executeUpdate();

            String sqlDropQuestion = "DROP TABLE IF EXISTS Question";
            connection.prepareStatement(sqlDropQuestion).executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
