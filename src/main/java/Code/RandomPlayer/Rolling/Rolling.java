package Code.RandomPlayer.Rolling;

import Code.CsvFile.GetDataFromFile;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rolling {
    private static int cnt = 0;
    private static List<String> name = new ArrayList<>();

    public Label renderRolling() {
        GetDataFromFile gdf = new GetDataFromFile();
        name = gdf.getNameFromFile("PlayerList.csv");
        Label rngLabel = new Label();
        rngLabel.setStyle("-fx-font-size: 200px; -fx-text-fill: red");
        rngLabel.setTranslateY(-200);

        Random rng = new Random();

        Thread rngThread = new Thread(() -> {
            int stop = 30;
            while (stop != 0) {
                Platform.runLater(() -> rngLabel.setText(name.get(cnt % name.size())));
                try {
                    cnt += rng.nextInt(100);
                    if (stop > 10)
                        Thread.sleep(100);
                    else if (stop > 4)
                        Thread.sleep(200);
                    else
                        Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                stop--;
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
