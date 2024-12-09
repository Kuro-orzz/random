package Code.CsvFile;

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
}
