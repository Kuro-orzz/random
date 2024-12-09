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
    exports Code.RandomPlayer.Rolling;
    opens Code.RandomPlayer.Rolling to javafx.fxml;
    exports Code.RandomPlayer.AddPlayer;
    opens Code.RandomPlayer.AddPlayer to javafx.fxml;
    exports Code.RandomPlayer.RemovePlayer;
    opens Code.RandomPlayer.RemovePlayer to javafx.fxml;
}