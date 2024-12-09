package Code;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class QuestionPackage {
    private final AppController controller;
    public final ImageView backgroundImage = new ImageView();
    private final Label choosePackage;

    public QuestionPackage(AppController controller) {
        this.controller = controller;
        this.choosePackage = new Label();
    }

    public Scene getQuestionPackageScene() {
        loadImage();
        setTextLabel();

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImage, choosePackage);

        Scene questionPackage = new Scene(root, 1920, 1080);
        questionPackage.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/Package.css")).toExternalForm()
        );

        return questionPackage;
    }

    public void loadImage() {
        backgroundImage.setImage(new Image(
                Objects.requireNonNull(Home.class.getResourceAsStream("/background.jpg"))
        ));
        backgroundImage.setFitWidth(1280);
        backgroundImage.setFitHeight(720);
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setStyle("-fx-opacity: 0.6");
    }

    public void setTextLabel() {
        choosePackage.setText("Chọn gói câu hỏi");
        choosePackage.getStyleClass().add("choose-package-label");
    }
}
