package Code.CsvFile;

import Code.Question.Question;
import com.opencsv.CSVWriter;

import java.io.IOException;

public class AppendDataToFile extends InitCsvFile {
    public AppendDataToFile() {}

    public void appendName(String fileName, String playerName) {
        try {
            CSVWriter writer = initCsvWriter(fileName, true);
            writer.writeNext(new String[]{playerName});
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void appendQuestion(String fileName, Question question) {
        try {
            CSVWriter writer = initCsvWriter(fileName, true);
            String[] data = {question.getQuestion(), question.getType()};
            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
