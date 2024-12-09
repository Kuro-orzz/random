package Code;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    private static int cnt = 1;
    public Label renderClock() {
        Label timeLabel = new Label();
        timeLabel.setStyle("-fx-font-size: 20px;");

        // Create clock using thread
        Thread clockThread = new Thread(() -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            while (true) {
                LocalTime currentTime = LocalTime.now();
//                String formattedTime = currentTime.format(formatter);
                Platform.runLater(() -> timeLabel.setText(String.valueOf(cnt % 6)));
                try {
                    cnt++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        clockThread.setDaemon(true);
        clockThread.start();

        return timeLabel;
    }
}