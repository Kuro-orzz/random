package Code.RandomPlayer.Rolling;

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

        VBox layout = new VBox(20, winnerText, buttonLayout);
        layout.getStyleClass().add("winner-alert-button");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 200);
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
        closeButton.setOnAction(e -> winner.close());
        removeButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white;");
        removeButton.setOnAction(e -> {

            winner.close();
        });
    }
}
