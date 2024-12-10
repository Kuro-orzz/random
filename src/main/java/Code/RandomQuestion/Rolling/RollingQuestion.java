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
    private static final List<Question> questions = new ArrayList<>();
    private static final List<String> topic = new ArrayList<>();

    public Label renderRolling() {
        GetDataFromFile gdf = new GetDataFromFile();
        List<Question> data = gdf.getQuestionFromFile("QuestionData.csv");
        for (Question q : data) {
            boolean t = true;
            for (String s : topic) {
                if (q.getTopic().equals(s)) {
                    t = false;
                    break;
                }
            }
            if (t) {
                questions.add(q);
            }
        }
        Label rngLabel = new Label();
        rngLabel.setStyle("-fx-font-size: 70px");
        rngLabel.setTranslateY(-250);

        Random rng = new Random();

        Thread rngThread = new Thread(() -> {
            int stop = 40;
            int cntQ = questions.size();
            while (stop != 0) {
                Platform.runLater(() -> rngLabel.setText(questions.get(cnt % cntQ).getQuestion()));
                try {
                    cnt += rng.nextInt(100);
                    if (stop > 25)
                        Thread.sleep(50);
                    else if (stop > 15)
                        Thread.sleep(100);
                    else if (stop > 10)
                        Thread.sleep(200);
                    else if (stop > 5)
                        Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                stop--;
            }
            cnt = cnt % cntQ;
            topic.add(questions.get(cnt % cntQ).getTopic());
            if (topic.size() == 3) {
                topic.remove(0);
            }
        });
        rngThread.setDaemon(true);
        rngThread.start();

        rngLabel.setWrapText(true);
        rngLabel.setMaxWidth(1575);
        rngLabel.setAlignment(Pos.CENTER);

        return rngLabel;
    }

}
