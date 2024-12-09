package Testing;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

    private int totalSegments = 6; // Tổng số phần trên bánh xe

    @Override
    public void start(Stage primaryStage) {
        // Tạo bánh xe
        Group wheel = createWheel(totalSegments);

        // Tạo kim
        Polygon pointer = createPointer();

        // Nút quay
        Button spinButton = new Button("Spin");
        spinButton.setFont(new Font("Arial", 16));

        // Thêm chức năng quay
        spinButton.setOnAction(event -> spinWheel(wheel));

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(new Group(wheel, pointer)); // Đặt bánh xe và kim trong cùng một Group
        root.setBottom(spinButton);
        BorderPane.setAlignment(spinButton, javafx.geometry.Pos.CENTER);

        // Scene
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Spin Wheel with Pointer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Tạo bánh xe với các phần
    private Group createWheel(int segments) {
        Group group = new Group();
        double radius = 200;
        double anglePerSegment = 360.0 / segments;

        for (int i = 0; i < segments; i++) {
            // Tạo từng phần của bánh xe
            Arc arc = new Arc(300, 300, radius, radius, i * anglePerSegment, anglePerSegment);
            arc.setType(ArcType.ROUND);
            arc.setFill(i % 2 == 0 ? Color.LIGHTBLUE : Color.LIGHTGREEN);
            arc.setStroke(Color.BLACK);

            // Thêm chữ trên từng phần
            Text text = new Text(
                    300 + Math.cos(Math.toRadians((i + 0.5) * anglePerSegment)) * radius / 1.5,
                    300 - Math.sin(Math.toRadians((i + 0.5) * anglePerSegment)) * radius / 1.5,
                    "S" + (i + 1)
            );
            text.setFont(new Font("Arial", 16));
            text.setFill(Color.BLACK);

            group.getChildren().addAll(arc, text);
        }
        return group;
    }

    // Tạo kim chỉ
    private Polygon createPointer() {
        Polygon pointer = new Polygon();
        pointer.getPoints().addAll(
                300.0, 100.0, // Đỉnh tam giác (phía trên bánh xe)
                290.0, 120.0, // Góc trái
                310.0, 120.0  // Góc phải
        );
        pointer.setFill(Color.RED);
        return pointer;
    }

    // Hàm quay bánh xe
    private void spinWheel(Group wheel) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(5), wheel);
        Random random = new Random();

        // Randomize góc quay
        double randomAngle = 360 * 5 + random.nextInt(360);

        rotate.setByAngle(randomAngle);
        rotate.setInterpolator(Interpolator.EASE_OUT); // Làm chậm dần khi kết thúc
        rotate.setOnFinished(event -> {
            // Xác định phần trúng
            double finalAngle = randomAngle % 360; // Lấy góc cuối cùng
            int segment = calculateSegment(finalAngle);
            System.out.println("Trúng phần: S" + segment);
        });
        rotate.play();
    }

    // Xác định phần trúng từ góc quay
    private int calculateSegment(double angle) {
        double anglePerSegment = 360.0 / totalSegments;
        int segment = (int) ((360 - angle + anglePerSegment / 2) % 360 / anglePerSegment) + 1;
        return segment;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
