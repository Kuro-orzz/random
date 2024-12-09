package Code.RandomPlayer;

import Code.CsvFile.GetDataFromFile;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Rolling {
    private static int cnt = 0;
    private static List<String> name = new ArrayList<>();

    public Label renderRolling() {
        GetDataFromFile gdf = new GetDataFromFile();
        name = gdf.getNameFromFile("PlayerList.csv");
        Label rngLabel = new Label();
        rngLabel.setStyle("-fx-font-size: 200px; -fx-text-fill: red");
        rngLabel.setTranslateY(-200);

        Thread rngThread = new Thread(() -> {
            int stop = cnt + 12;
            while (cnt < stop) {
                Platform.runLater(() -> rngLabel.setText(name.get(cnt % name.size())));
                try {
                    cnt++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            String player = name.get(cnt % name.size());
            Platform.runLater(() -> new WinnerAlert("Winner", player).showAnnouncement());
            cnt = cnt % name.size();
        });
        rngThread.setDaemon(true);
        rngThread.start();

        return rngLabel;
    }
}
