package Testing;

import Code.CsvFile.GetDataFromFile;
import Code.CsvFile.InitCsvFile;
import Code.Question.Question;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        InitCsvFile initCsvFile = new InitCsvFile();
//        initCsvFile.initQuestionData("QuestionData.csv");
        List<Question> questionList = new GetDataFromFile().getQuestionFromFile("QuestionData.csv");
        for (Question question : questionList) {
            System.out.println(question.getQuestion() + "\t\t\t\t" + question.getType());
        }
    }
}
