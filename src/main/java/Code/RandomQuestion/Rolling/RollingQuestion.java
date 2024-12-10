package Code.RandomQuestion.Rolling;

import Code.CsvFile.GetDataFromFile;
import Code.Question.Question;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RollingQuestion {
    private static  int cnt = 0;
    private static List<Question> questions = new ArrayList<>();

    public Label renderRolling() {
        GetDataFromFile gdf = new GetDataFromFile();
        questions = gdf.getQuestionFromFile("QuestionData.csv");
        Label rngLabel = new Label();
        rngLabel.setStyle("-fx-font-size: 50px");
        rngLabel.setTranslateY(-200);

        Random rng = new Random();

        Thread rngThread = new Thread(() -> {
            int stop = 35;
            while (stop != 0) {
                Platform.runLater(() -> rngLabel.setText(questions.get(cnt % questions.size()).getQuestion()));
                try {
                    cnt += rng.nextInt(100);
                    if (stop > 15)
                        Thread.sleep(50);
                    else if (stop > 7)
                        Thread.sleep(100);
                    else
                        Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                stop--;
            }
            cnt = cnt % questions.size();
        });
        rngThread.setDaemon(true);
        rngThread.start();

        rngLabel.setWrapText(true);
        rngLabel.setPrefWidth(1500);
        rngLabel.setAlignment(Pos.CENTER);

        return rngLabel;
    }

}
