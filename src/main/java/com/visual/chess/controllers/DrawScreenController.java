package com.visual.chess.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DrawScreenController {
    public static Stage boardStageSecond;
    public Pane drawBG;
    public Label drawLabel;
    public Button exitButton;

    public void onExitButtonClick(ActionEvent actionEvent){
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}