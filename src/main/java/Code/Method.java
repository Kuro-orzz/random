package Code;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Method {
    public void loadImage(ImageView backgroundImage, ImageView returnButton) {
        backgroundImage.setImage(new Image(
                Objects.requireNonNull(Home.class.getResourceAsStream("/gif.gif"))
        ));
        backgroundImage.setFitWidth(1920);
        backgroundImage.setFitHeight(1080);
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setStyle("-fx-opacity: 0.2");

        if (returnButton == null) {
            return;
        }

        returnButton.setImage(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/return.png"))
        ));
        returnButton.setTranslateX(-850);
        returnButton.setTranslateY(-450);
        returnButton.setPreserveRatio(true);
        returnButton.setFitWidth(100);
    }

    public void setReturnButtonAction(AppController controller, ImageView returnButton, Scene scene) {
        setButtonAnimation(returnButton);
        returnButton.setOnMouseClicked(e -> controller.setScene(scene));
    }

    public void setButtonAnimation(Node node) {
        node.setOnMouseEntered(e -> {
            node.setScaleX(1.2);
            node.setScaleY(1.2);
        });

        node.setOnMouseExited(e -> {
            node.setScaleX(1.0);
            node.setScaleY(1.0);
        });
    }
}
