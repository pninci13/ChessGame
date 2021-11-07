package com.visual.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("main-view.fxml"));
            Scene landingScene = new Scene(fxmlLoader.load(), 500, 350);
            Image chessIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/icon.png")));

            stage.getIcons().add(chessIcon);
            stage.setResizable(false);
            stage.setTitle("VP Chess");
            stage.setScene(landingScene);
            stage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


}