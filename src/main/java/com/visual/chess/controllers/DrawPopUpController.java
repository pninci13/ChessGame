package com.visual.chess.controllers;

import com.visual.chess.ConfirmationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawPopUpController {
    public Button yesButton;
    public Button noButton;
    public Pane popUpStage;

    private ConfirmationListener confirmationListener;

    public void setConfirmationListener(ConfirmationListener confirmationListener){
        this.confirmationListener = confirmationListener;
    }

    @FXML
    public void onYesButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        confirmationListener.onYesClick();
    }

    @FXML
    public void onNoButtonClick(ActionEvent actionEvent){
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
