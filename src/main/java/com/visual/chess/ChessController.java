package com.visual.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ChessController {
    public Button playButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onPlayButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}