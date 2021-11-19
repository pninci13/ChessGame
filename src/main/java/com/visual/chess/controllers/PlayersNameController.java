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

public class PlayersNameController {
    public Button goBackButton;

    public void mainScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

        stage.setResizable(false);
        stage.setTitle("VP Chess");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void boardScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/chessBoard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 800);

        stage.setResizable(false);
        stage.setTitle("VP Chess");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent actionEvent) {
        try {
            mainScreen(actionEvent);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void onGoButtonClick(ActionEvent actionEvent) {
        try {
            boardScreen(actionEvent);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
