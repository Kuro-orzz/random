package Code.RandomPlayer.Rolling;

import Code.CsvFile.GetDataFromFile;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RollingPlayer {
    private static int cnt = 0;
    private static List<String> name = new ArrayList<>();

    public Label renderRolling() {
        GetDataFromFile gdf = new GetDataFromFile();
        name = gdf.getNameFromFile("PlayerList.csv");

        if (name.isEmpty()) {
            Label t = new Label("The end!");
            t.setStyle("-fx-font-size: 200px; -fx-text-fill: red");
            t.setTranslateY(-200);
            return t;
        }

        Label rngLabel = new Label();
        rngLabel.setStyle("-fx-font-size: 200px; -fx-text-fill: red");
        rngLabel.setTranslateY(-200);

        Thread rngThread = new Thread(() -> {
            Random rng = new Random();
            int stop = 50;

            if (name.size() == 1) {
                stop = 0;
            }

            while (stop != 0) {
                Platform.runLater(() -> rngLabel.setText(name.get(cnt % name.size())));
                try {
                    cnt += rng.nextInt(100);
                    if (stop > 30)
                        Thread.sleep(50);
                    else if (stop > 15)
                        Thread.sleep(100);
                    else if (stop > 6)
                        Thread.sleep(200);
                    else if (stop > 3)
                        Thread.sleep(300);
                    else
                        Thread.sleep(400);
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

//    public void firework
}
