package Code.RandomPlayer.Player;

import Code.AppController;
import Code.CsvFile.AppendDataToFile;
import Code.CsvFile.GetDataFromFile;
import Code.Home;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Objects;

public class AddPlayer extends Method {
    private final AppController controller;
    private final ImageView backgroundImage;
    private final ImageView returnButton;
    private final TextField nameField;
    private final Button add;

    public AddPlayer(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.returnButton = new ImageView();
        this.nameField = new TextField();
        nameField.setPromptText("good luck ╰(*°▽°*)╯");
        this.add = new Button("Add");
    }

    public Scene getAddPlayerScene() {
        loadImage(backgroundImage, returnButton);
        getUI(nameField, add);
        setReturnButtonAction(controller, returnButton);
        setAddButtonAction();

        return initScene(backgroundImage, returnButton, nameField, add);
    }

    public void setAddButtonAction() {
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) {
                nameField.setText(newValue.substring(0, 20));
            }
        });

        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addAction();
            }
        });

        setButtonAnimation(add);
        add.setOnAction(e -> {
            addAction();
        });
    }

    public void addAction() {
        if (nameField.getText().isEmpty()) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Text is empty",
                    "Please type player name"
            );
            return;
        }
        AppendDataToFile newData = new AppendDataToFile();
        newData.appendName("PlayerList.csv", nameField.getText());
        showAlert(Alert.AlertType.INFORMATION,
                "Added Successful",
                "Success full added " + nameField.getText() + " to list"
        );
        nameField.setText("");
        controller.setScene(getAddPlayerScene());
    }
}
