package Code.CsvFile;

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
}
