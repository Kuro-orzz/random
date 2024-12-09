package Code.CsvFile;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InitCsvFile {
    public InitCsvFile() {}

    public CSVWriter initCsvWriter(String fileName, boolean isAppend) {
        try {
            String filePath = "src/main/resources/" + fileName;
            File file = new File(filePath);
            FileWriter outfile = new FileWriter(file, isAppend);
            return new CSVWriter(outfile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void initNameData(String fileName) {
        try {
            CSVWriter writer = initCsvWriter(fileName, false);
            writer.writeNext(new String[]{"PlayerName"});
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initQuestionData(String fileName) {
        try {
            CSVWriter writer = initCsvWriter(fileName, false);
            writer.writeNext(new String[]{"Question", "Type"});
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
