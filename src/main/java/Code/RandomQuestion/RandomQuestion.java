package Code.RandomQuestion;

import Code.AppController;
import Code.Home;
import Code.Method;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class RandomQuestion extends Method {
    private AppController controller;
    private ImageView backgroundImage;
    private ImageView returnButton;
    private Button rollingButton;

    public RandomQuestion(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.returnButton = new ImageView();
        this.rollingButton = new Button("Roll");
    }

    public Scene getRandomQuestionScene() {
        loadImage(backgroundImage, returnButton);
        getUI();
        Home newHome = new Home(controller);
        setReturnButtonAction(controller, returnButton, newHome.getHomeScene());
        setRollingButtonAction();

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImage, returnButton, rollingButton);

        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomQuestion.css")).toExternalForm()
        );

        return scene;
    }

    public void getUI() {
        setButtonAnimation(returnButton);
        returnButton.getStyleClass().add("return");

        setButtonAnimation(rollingButton);
        rollingButton.getStyleClass().add("rolling-button");
    }

    public void setRollingButtonAction() {
        // rolling button action
    }
}
