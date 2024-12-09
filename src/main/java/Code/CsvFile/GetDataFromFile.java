package Code.CsvFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetDataFromFile {
    public GetDataFromFile() {}

    public CSVReader initCsvReader(String fileName) {
        try {
            String filePath = "src/main/resources/" + fileName;
            return new CSVReader(new FileReader(filePath));
        } catch (IOException e) {
            System.out.println("File not found in resources: " + fileName);
            return null;
        }
    }

    public List<String[]> readData(CSVReader reader) {
        List<String[]> data = new ArrayList<>();
        try {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
            return data;
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading csv file: " + e.getMessage());
        }
        return null;
    }

    public List<String> getNameFromFile(String fileName) {
        try (CSVReader reader = initCsvReader(fileName)) {
            List<String[]> records = readData(reader);
            List<String> books = new ArrayList<>();
            for (int i = 1; i < records.size(); i++) {
                books.add(records.get(i)[0]);
            }
            return books;
        } catch (IOException e) {
            System.out.println("File not found in resources: " + e.getMessage());
        }
        return null;
    }
}
