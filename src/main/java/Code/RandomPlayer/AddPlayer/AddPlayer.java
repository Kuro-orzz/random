package Code.RandomPlayer.AddPlayer;

import Code.AppController;
import Code.CsvFile.AppendDataToFile;
import Code.Method;
import Code.RandomPlayer.AddRemoveMethod;
import Code.RandomPlayer.RandomPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class AddPlayer extends AddRemoveMethod {
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
        nameField.setPromptText("Good luck ╰(*°▽°*)╯");
        this.add = new Button("Add");
    }

    public Scene getAddPlayerScene() {
        new Method().loadImage(backgroundImage, returnButton);
        RandomPlayer randomPlayer = new RandomPlayer(controller);
        setReturnButtonAction(controller, returnButton, randomPlayer.getRandomPlayerScene());
        getUI();
        setTextFieldEvent();
        setAddButtonAction();

        return initScene(backgroundImage, returnButton, nameField, add);
    }

    public void getUI() {
        nameField.getStyleClass().add("name-field");
        add.getStyleClass().add("op-button");
    }

    public void setTextFieldEvent() {
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                nameField.setText(newValue.substring(0, 30));
            }
        });

        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addAction();
            }
        });
    }

    public void setAddButtonAction() {
        setButtonAnimation(add);
        add.setOnAction(e -> addAction());
    }

    public void addAction() {
        if (nameField.getText().isEmpty()) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "TextField is empty",
                    "Please type player name"
            );
            return;
        }
        AppendDataToFile newData = new AppendDataToFile();
        newData.appendName("PlayerList.csv", nameField.getText());
        showAlert(Alert.AlertType.INFORMATION,
                "Added Successful",
                "Successfully added \"" + nameField.getText() + "\" to list"
        );
        nameField.setText("");
        controller.setScene(getAddPlayerScene());
    }
}
