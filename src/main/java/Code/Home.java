package Code;

import Code.RandomPlayer.RandomPlayer;
import Code.RandomQuestion.RandomQuestion;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class Home {
    private final AppController controller;
    public final ImageView backgroundImage = new ImageView();
    private final Label welcomeText;
    private final Button randomPlayer;
    private final Button selectQuestion;
    private final Button randomQuestion;

    public Home(AppController controller) {
        this.controller = controller;
        this.welcomeText = new Label();
        this.randomPlayer = new Button();
        this.selectQuestion = new Button();
        this.randomQuestion = new Button();
        loadImage();
    }

    public Scene getHomeScene() {
        setTextLabel();

        setButtonAction();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImage, welcomeText,
                randomPlayer, selectQuestion, randomQuestion);

        Scene homeScene = new Scene(stackPane, 1920, 1080);
        homeScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/Home.css")).toExternalForm()
        );

        return homeScene;
    }

    public void loadImage() {
        backgroundImage.setImage(new Image(
                Objects.requireNonNull(Home.class.getResourceAsStream("/gif.gif"))
        ));
        backgroundImage.setFitWidth(1920);
        backgroundImage.setFitHeight(1080);
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setStyle("-fx-opacity: 0.3");
    }

    public void setTextLabel() {
        welcomeText.setText("Vòng quay nhân phẩm");
        welcomeText.getStyleClass().add("welcome-text");
    }

    public void setButtonAction() {
        randomPlayer.setText("Random player");
        randomPlayer.getStyleClass().add("random-player");
        setButtonAnimation(randomPlayer);
        randomPlayer.setOnMouseClicked(event -> {
            Scene randomPlayer = new RandomPlayer(controller).getRandomPlayerScene();
            controller.setScene(randomPlayer);
            // có add player and display list player
            // Spin button to roll
        });

        selectQuestion.setText("Select question");
        selectQuestion.getStyleClass().add("select-button");
        setButtonAnimation(selectQuestion);
        selectQuestion.setOnMouseClicked(event -> {
            System.out.println("select");
            // go to select screen (question package)
            // đợi chốt xem có những chủ đề gì
        });

        randomQuestion.setText("Random question");
        randomQuestion.getStyleClass().add("random-button");
        setButtonAnimation(randomQuestion);
        randomQuestion.setOnMouseClicked(event -> {
            Scene randomQuestion = new RandomQuestion(controller).getRandomQuestionScene();
            controller.setScene(randomQuestion);
        });
    }

    public void setButtonAnimation(Button button) {
        button.setOnMouseEntered(e -> {
            button.setScaleX(1.2); // Increase width
            button.setScaleY(1.2); // Increase height
        });

        button.setOnMouseExited(e -> {
            button.setScaleX(1.0); // Reset width
            button.setScaleY(1.0); // Reset height
        });
    }
}
