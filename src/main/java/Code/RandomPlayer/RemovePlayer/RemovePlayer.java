package Code.RandomPlayer.RemovePlayer;

import Code.AppController;
import Code.CsvFile.GetDataFromFile;
import Code.CsvFile.UpdateDataFromListToFile;
import Code.RandomPlayer.Method;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.List;

public class RemovePlayer extends Method {
    private final AppController controller;
    private final ImageView backgroundImage;
    private final TextField nameField;
    private final Button remove;
    private final ImageView returnButton;

    public RemovePlayer(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.nameField = new TextField();
        nameField.setPromptText("good luck (❁´◡`❁)");
        this.remove = new Button("Remove");
        this.returnButton = new ImageView();
    }

    public Scene getRemovePlayerScene() {
        loadImage(backgroundImage, returnButton);
        getUI(nameField, remove);
        setReturnButtonAction(controller, returnButton);
        setRemoveButtonAction();

        return initScene(backgroundImage, returnButton, nameField, remove);
    }

    public void setRemoveButtonAction() {
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

        setButtonAnimation(remove);
        remove.setOnAction(e -> {
            removeAction();
        });
    }

    public void removeAction() {
        List<String> data = new GetDataFromFile().getNameFromFile("PlayerList.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(nameField.getText())) {
                data.remove(i);
                UpdateDataFromListToFile upd = new UpdateDataFromListToFile();
                upd.updateName("PlayerList.csv", data);
                showAlert(Alert.AlertType.INFORMATION,
                        "Remove successful;",
                        "Success full removed \"" + nameField.getText() + "\" from list"
                );
                nameField.setText("");
                controller.setScene(getRemovePlayerScene());
                return;
            }
        }
        showAlert(
                Alert.AlertType.ERROR,
                "ERROR",
                "Player " + nameField.getText() + " not found"
        );
    }
}