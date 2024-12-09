package Code.RandomPlayer;

import Code.AppController;
import Code.Home;
import Code.RandomPlayer.AddPlayer.AddPlayer;
import Code.RandomPlayer.RemovePlayer.RemovePlayer;
import Code.RandomPlayer.Rolling.Rolling;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class RandomPlayer {
    private final AppController controller;
    private final ImageView backgroundImage;
    private final Button rolling;
    private final Button addPlayer;
    private final Button removePlayer;

    public RandomPlayer(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.rolling = new Button("Rolling");
        this.addPlayer = new Button("Add Player");
        this.removePlayer = new Button("Remove Player");
    }

    public Scene getRandomPlayerScene() {
        loadImage();

        getUI();
        setButtonAction();

        StackPane stackPane = new StackPane(backgroundImage, rolling, addPlayer, removePlayer);

        Scene randomPlayerScene = new Scene(stackPane, 1920, 1080);
        randomPlayerScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );

        return randomPlayerScene;
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

    public void getUI() {
        rolling.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );
        rolling.getStyleClass().add("rolling-button");
        setButtonAnimation(rolling);

        addPlayer.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );
        addPlayer.getStyleClass().add("add-player-button");
        setButtonAnimation(addPlayer);

        removePlayer.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );
        removePlayer.getStyleClass().add("remove-player-button");
        setButtonAnimation(removePlayer);
    }

    public void setButtonAction() {
        rolling.setOnAction(e -> {
            Scene root = getRandomPlayerScene();
            StackPane stackPane = new StackPane(root.getRoot(), new Rolling().renderRolling());
            Scene randomPlayerScene = new Scene(stackPane, 1920, 1080);
            controller.setScene(randomPlayerScene);
        });

        addPlayer.setOnAction(e -> {
            controller.setScene(new AddPlayer(controller).getAddPlayerScene());
        });

        removePlayer.setOnAction(e -> {
            controller.setScene(new RemovePlayer(controller).getRemovePlayerScene());
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
