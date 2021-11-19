package com.visual.chess.controllers;

import com.visual.chess.ChessApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessController {
    public Button playButton;

    public void playersScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/playersName-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

        stage.setResizable(false);
        stage.setTitle("VP Chess");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onPlayButtonClick(ActionEvent actionEvent) {
        try {
            playersScreen(actionEvent);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}