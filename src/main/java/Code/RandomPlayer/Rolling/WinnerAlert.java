package Code.RandomPlayer.Rolling;

import Code.CsvFile.GetDataFromFile;
import Code.CsvFile.UpdateDataFromListToFile;
import Code.Method;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class WinnerAlert {
    private final Stage winner;
    private final Text winnerText;
    private final Button closeButton;
    private final Button removeButton;

    public WinnerAlert(String windowTitle, String winnerName) {
        this.winner = new Stage();
        winner.setTitle(windowTitle);
        this.winnerText = new Text(winnerName);
        this.closeButton = new Button("Close");
        this.removeButton = new Button("Remove");
    }

    public void showAnnouncement() {
        winner.initModality(Modality.APPLICATION_MODAL);
        winner.setResizable(false);

        setWinnerText();
        setButtonAction();

        HBox buttonLayout = new HBox(10, closeButton, removeButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, winnerText, buttonLayout);
        layout.getStyleClass().add("winner-alert-button");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );
        winner.setScene(scene);

        winner.showAndWait();
    }

    public void setWinnerText() {
        winnerText.setFill(Color.WHITE);
        winnerText.setFont(Font.font("Arial", 40));
    }

    public void setButtonAction() {
        Method method = new Method();

        method.setButtonAnimation(closeButton);
        closeButton.setStyle("-fx-font-size: 15px");
        closeButton.setOnAction(e -> winner.close());

        method.setButtonAnimation(removeButton);
        removeButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 15px");
        removeButton.setOnAction(e -> {
            String winnerName = winnerText.getText();
            List<String> list = new GetDataFromFile().getNameFromFile("PlayerList.csv");
            for (String s : list) {
                if (s.equals(winnerName)) {
                    list.remove(s);
                    break;
                }
            }
            UpdateDataFromListToFile update = new UpdateDataFromListToFile();
            update.updateName("PlayerList.csv", list);
            winner.close();
        });
    }
}
