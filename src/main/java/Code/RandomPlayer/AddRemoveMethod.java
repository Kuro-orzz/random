package Code.RandomPlayer;

import Code.AppController;
import Code.CsvFile.GetDataFromFile;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Objects;

public class AddRemoveMethod {
    public StackPane curPlayerList() {
        List<String> data = new GetDataFromFile().getNameFromFile("PlayerList.csv");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(30); // Khoảng cách giữa các cột
        gridPane.setVgap(10); // Khoảng cách giữa các hàng

        int columns = 4;
        for (int i = 0; i < data.size(); i++) {
            int row = i / columns; // Hàng hiện tại
            int col = i % columns; // Cột hiện tại

            Label label = new Label(data.get(i));

            gridPane.add(label, col, row);
        }
        gridPane.getStyleClass().add("cur-list");
        return new StackPane(gridPane);
    }

    public Pane createPane(TextField textField) {
        Pane pane = new Pane(textField);
        pane.setTranslateX(750);
        pane.setTranslateY(630);
        return pane;
    }

    public Scene initScene(ImageView backgroundImage, ImageView returnButton, TextField nameField, Button button) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImage,
                curPlayerList(), createPane(nameField), button, returnButton);

        Scene scene = new Scene(stackPane, 1920, 1080);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );

        return scene;
    }

    public void setReturnButtonAction(AppController controller, ImageView returnButton, Scene scene) {
        setButtonAnimation(returnButton);
        returnButton.setOnMouseClicked(e -> controller.setScene(scene));
    }

    public void setButtonAnimation(Node node) {
        node.setOnMouseEntered(e -> {
            node.setScaleX(1.2);
            node.setScaleY(1.2);
        });

        node.setOnMouseExited(e -> {
            node.setScaleX(1.0);
            node.setScaleY(1.0);
        });
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
