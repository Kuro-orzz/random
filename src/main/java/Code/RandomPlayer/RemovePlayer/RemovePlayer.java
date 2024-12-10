package Code.RandomPlayer.RemovePlayer;

import Code.AppController;
import Code.CsvFile.GetDataFromFile;
import Code.CsvFile.UpdateDataFromListToFile;
import Code.Method;
import Code.RandomPlayer.AddRemoveMethod;
import Code.RandomPlayer.RandomPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.List;

public class RemovePlayer extends AddRemoveMethod {
    private final AppController controller;
    private final ImageView backgroundImage;
    private final TextField nameField;
    private final Button remove;
    private final ImageView returnButton;

    public RemovePlayer(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.nameField = new TextField();
        nameField.setPromptText("(～￣▽￣)～");
        this.remove = new Button("Remove");
        this.returnButton = new ImageView();
    }

    public Scene getRemovePlayerScene() {
        new Method().loadImage(backgroundImage, returnButton);
        RandomPlayer randomPlayer = new RandomPlayer(controller);
        setReturnButtonAction(controller, returnButton, randomPlayer.getRandomPlayerScene());
        getUI();
        setTextFieldEvent();
        setRemoveButtonAction();

        return initScene(backgroundImage, returnButton, nameField, remove);
    }

    public void getUI() {
        nameField.getStyleClass().add("name-field");
        remove.getStyleClass().add("op-button");
    }

    public void setTextFieldEvent() {
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                nameField.setText(newValue.substring(0, 30));
            }
        });

        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                removeAction();
            }
        });
    }

    public void setRemoveButtonAction() {
        setButtonAnimation(remove);
        remove.setOnAction(e -> removeAction());
    }

    public void removeAction() {
        List<String> data = new GetDataFromFile().getNameFromFile("PlayerList.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(nameField.getText())) {
                data.remove(i);
                UpdateDataFromListToFile upd = new UpdateDataFromListToFile();
                upd.updateName("PlayerList.csv", data);
                showAlert(Alert.AlertType.INFORMATION,
                        "Removed successful;",
                        "Successfully removed \"" + nameField.getText() + "\" from list"
                );
                nameField.setText("");
                controller.setScene(getRemovePlayerScene());
                return;
            }
        }
        showAlert(
                Alert.AlertType.ERROR,
                "Error",
                "Not found \"" + nameField.getText() + "\" in list"
        );
    }
}
