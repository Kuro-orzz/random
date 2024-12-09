package Code.CsvFile;

import Code.Question.Question;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.util.List;

public class UpdateDataFromListToFile extends InitCsvFile {
    public UpdateDataFromListToFile() {}

    public void updateName(String fileName, List<String> playersList) {
        try {
            CSVWriter writer = initCsvWriter(fileName, false);
            writer.writeNext(new String[]{"playerName"});
            for (String name : playersList) {
                writer.writeNext(new String[]{name});
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateQuestion(String fileName, List<Question> questionList) {
        try {
            CSVWriter writer = initCsvWriter(fileName, false);
            writer.writeNext(new String[]{"playerName"});
            for (Question question : questionList) {
                String[] data = {question.getQuestion(), question.getType()};
                writer.writeNext(data);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
