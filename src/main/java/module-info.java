module org.example.random_question {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires opencsv;

    opens Code to javafx.fxml;
    exports Code;
    exports Code.RandomPlayer;
    opens Code.RandomPlayer to javafx.fxml;

    opens Testing to javafx.fxml;
    exports Testing;
    exports Code.RandomPlayer.Player;
    opens Code.RandomPlayer.Player to javafx.fxml;
}