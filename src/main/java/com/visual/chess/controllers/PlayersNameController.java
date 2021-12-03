package com.visual.chess.controllers;

import com.visual.chess.ChessApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayersNameController {
    public Button goBackButton;
    public TextField player1TextField;
    public TextField player2TextField;
    public Button goButton;

    public static String whitePiecePlayer, blackPiecePlayer;

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

    public static Stage boardStage;

    public void boardScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        boardStage = stage;
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
            getPlayersNames();
            System.out.println(whitePiecePlayer);
            System.out.println(blackPiecePlayer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void getPlayersNames(){
        whitePiecePlayer = player1TextField.getText();
        blackPiecePlayer = player2TextField.getText();
    }
}
