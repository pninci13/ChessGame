module com.visual.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.visual.chess to javafx.fxml;
    exports com.visual.chess;
    opens com.visual.chess.controllers to javafx.fxml;
    exports com.visual.chess.controllers;
    opens com.visual.chess.assets to javafx.fxml;
    exports com.visual.chess.assets;

//    opens com.visual.chess.controllers to javafx.fxml;
//    exports com.visual.chess.controllers;

}