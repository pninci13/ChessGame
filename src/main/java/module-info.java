module com.visual.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.visual.chess to javafx.fxml;
    exports com.visual.chess;
}