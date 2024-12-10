package Code.RandomQuestion;

import Code.AppController;
import Code.Home;
import Code.Method;
import Code.RandomQuestion.Rolling.RollingQuestion;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class RandomQuestion extends Method {
    private final AppController controller;
    private final ImageView backgroundImage;
    private final ImageView returnButton;
    private final Button rollingButton;

    public RandomQuestion(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.returnButton = new ImageView();
        this.rollingButton = new Button("Roll");
        loadImage(backgroundImage, returnButton);
    }

    public Scene getRandomQuestionScene() {
        getUI();
        setReturnButtonAction(controller, returnButton, new Home(controller).getHomeScene());
        setRollingButtonAction();

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImage, returnButton, rollingButton);

        return new Scene(root, 1920, 1080);
    }

    public void getUI() {
        setButtonAnimation(returnButton);
        returnButton.setTranslateX(-850);
        returnButton.setTranslateY(-450);

        setButtonAnimation(rollingButton);
        rollingButton.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomQuestion.css")).toExternalForm()
        );
        rollingButton.getStyleClass().add("rolling-button");
    }

    public void setRollingButtonAction() {
        rollingButton.setOnMouseClicked(e -> {
            Scene scene = getRandomQuestionScene();
            StackPane root = new StackPane(scene.getRoot(), new RollingQuestion().renderRolling());
            controller.setScene(new Scene(root, 1920, 1080));
        });
    }
}
