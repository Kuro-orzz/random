package Testing;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Tạo Timeline để tự động sinh pháo hoa
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    Random random = new Random();
                    double x = random.nextDouble() * WIDTH;
                    double y = random.nextDouble() * HEIGHT;
                    createFireworks(root, x, y);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.setTitle("Random Fireworks Effect");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createFireworks(Pane root, double x, double y) {
        int particleCount = 20;
        double maxRadius = 100;

        for (int i = 0; i < particleCount; i++) {
            // Tạo các hạt pháo hoa
            Line line = new Line(x, y, x, y);
            line.setStroke(Color.hsb(Math.random() * 360, 1, 1));
            line.setStrokeWidth(2);

            // Tạo hiệu ứng cho mỗi hạt
            double angle = 2 * Math.PI * i / particleCount;
            double endX = x + maxRadius * Math.cos(angle);
            double endY = y + maxRadius * Math.sin(angle);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(line.endXProperty(), x),
                            new KeyValue(line.endYProperty(), y)),
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(line.endXProperty(), endX),
                            new KeyValue(line.endYProperty(), endY),
                            new KeyValue(line.opacityProperty(), 0))
            );
            timeline.setOnFinished(e -> root.getChildren().remove(line));
            timeline.play();

            root.getChildren().add(line);
        }

        // Hiệu ứng tâm pháo hoa
        Circle center = new Circle(x, y, 5, Color.WHITE);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), center);
        scaleTransition.setToX(5);
        scaleTransition.setToY(5);
        scaleTransition.setOnFinished(e -> root.getChildren().remove(center));
        scaleTransition.play();

        root.getChildren().add(center);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
