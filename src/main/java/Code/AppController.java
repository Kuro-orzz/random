package Code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class AppController extends Application {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Image icon = new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/appIcon.png"))
        );
        stage.getIcons().add(icon);
        stage.setTitle("Vòng quay nhân phẩm");

//        StackPane root = new StackPane(new Clock().renderClock());
        Scene homeScene = new Home(this).getHomeScene();

        stage.setScene(homeScene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
        setPrimaryStage(stage);
    }

    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }
}