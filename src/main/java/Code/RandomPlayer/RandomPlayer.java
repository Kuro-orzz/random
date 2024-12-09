package Code.RandomPlayer;

import Code.AppController;
import Code.Home;
import Code.RandomPlayer.AddPlayer.AddPlayer;
import Code.RandomPlayer.RemovePlayer.RemovePlayer;
import Code.RandomPlayer.Rolling.RollingPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class RandomPlayer extends Method {
    private final AppController controller;
    private final ImageView backgroundImage;
    private final ImageView returnButton;
    private final Button rolling;
    private final Button addPlayer;
    private final Button removePlayer;

    public RandomPlayer(AppController controller) {
        this.controller = controller;
        this.backgroundImage = new ImageView();
        this.returnButton = new ImageView();
        this.rolling = new Button("Rolling");
        this.addPlayer = new Button("Add Player");
        this.removePlayer = new Button("Remove Player");
    }

    public Scene getRandomPlayerScene() {
        loadImage(backgroundImage, returnButton);
        getUI();
        setButtonAction();

        StackPane stackPane = new StackPane(backgroundImage,
                returnButton, rolling, addPlayer, removePlayer);

        Scene randomPlayerScene = new Scene(stackPane, 1920, 1080);
        randomPlayerScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/RandomPlayer.css")).toExternalForm()
        );

        return randomPlayerScene;
    }

    public void getUI() {
        rolling.getStyleClass().add("rolling-button");
        setButtonAnimation(rolling);

        addPlayer.getStyleClass().add("add-player-button");
        setButtonAnimation(addPlayer);

        removePlayer.getStyleClass().add("remove-player-button");
        setButtonAnimation(removePlayer);
    }

    public void setButtonAction() {
        setButtonAnimation(returnButton);
        returnButton.setOnMouseClicked(e -> controller.setScene(new Home(controller).getHomeScene()));

        rolling.setOnAction(e -> {
            Scene root = getRandomPlayerScene();
            StackPane stackPane = new StackPane(root.getRoot(), new RollingPlayer().renderRolling());
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
}
