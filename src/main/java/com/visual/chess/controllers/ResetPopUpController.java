package com.visual.chess.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ResetPopUpController {
    public Button yesButton;
    public Button noButton;
    public Pane popUpStage;

    @FXML
    public void onYestButtonClick(ActionEvent actionEvent){
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        
    }

    @FXML
    public void onNoButtonClick(ActionEvent actionEvent){
       Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
       stage.close();
   }
}
