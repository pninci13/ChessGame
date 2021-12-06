package com.visual.chess.controllers;

import com.visual.chess.ChessApplication;
import com.visual.chess.ConfirmationListener;
import com.visual.chess.assets.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;

public class ChessBoardController {

    public Label vezDeLabel;
    public Pane boardBG;
    public Label columnLabel;
    public Label playerNameLabel;
    public Timer timer;

    @FXML
    private Button surrenderButton;

    @FXML
    private Button drawButton;

    @FXML
    private Button resetButton;

    public int currentRow, currentColumn;
    public static int targetRow, targetColumn, sourceToTarget;
    public static int changeXPosition, changeYPosition;
    public static int pieceColor;
    public static int fiftyMoves = 4, piecesAmount = 0;
    public boolean gameFinished = false;

    public static ArrayList<Coordinate> allCheckBlocks = new ArrayList<>();
    Node currentNode, targetNode;

    static Button[][] buttonMatrix = new Button[8][8];
    public static Tile[][] tileMatrix = new Tile[8][8];

    public static ArrayList<Coordinate> threatPiecesCoordinate = new ArrayList<>();

    @FXML
    public GridPane chessBoard;

    @FXML
    public void initialize() {
        chessBoard.isGridLinesVisible();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = new Tile();
                tileMatrix[i][j] = tile;

                whitePieces(i, j);
                blackPieces(i, j);

                if (i == 2 || i == 3 || i == 4 || i == 5) {
                    Button button = createNewButton(i, j);
                    chessBoard.add(button, j, i);
                    buttonMatrix[i][j] = button;
                }
            }
        }
        gameFinished = false;
        playerNameLabel.setText(PlayersNameController.whitePiecePlayer);
    }

    public void whitePieces(int i, int j) {
        String url;

        if (i == 6) {
            url = "com/visual/chess/images/chessPiecesImages/WhitePawn.png";
            Button button = createNewButton(i, j);
            button = addPieces(button, url);
            chessBoard.add(button, j, 6);
            buttonMatrix[6][j] = button;
            Pawn pawn = new Pawn(Piece.WHITE);
            pawn.setCoordinate(i, j);
            tileMatrix[6][j].setPiece(pawn);
        }

        if (i == 7) {
            if (j == 0 || j == 7) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteRook.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                Rook rook = new Rook(Piece.WHITE);
                rook.setCoordinate(i, j);
                tileMatrix[7][j].setPiece(rook);
            }

            if (j == 1 || j == 6) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteKnight.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                Knight knight = new Knight(Piece.WHITE);
                knight.setCoordinate(i, j);
                tileMatrix[7][j].setPiece(knight);
            }

            if (j == 2 || j == 5) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteBishop.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                Bishop bishop = new Bishop(Piece.WHITE);
                bishop.setCoordinate(i, j);
                tileMatrix[7][j].setPiece(bishop);
            }

            if (j == 3) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteQueen.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                Queen queen = new Queen(Piece.WHITE);
                queen.setCoordinate(i, j);
                tileMatrix[7][j].setPiece(queen);
            }

            if (j == 4) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteKing.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                King king = new King(Piece.WHITE);
                king.setCoordinate(i, j);
                tileMatrix[7][j].setPiece(king);
            }
        }
    }

    public void blackPieces(int i, int j) {
        String url;

        if (i == 1) {
            url = "com/visual/chess/images/chessPiecesImages/BlackPawn.png";
            Button button = createNewButton(i, j);
            button = addPieces(button, url);
            chessBoard.add(button, j, 1);
            buttonMatrix[1][j] = button;
            Pawn pawn = new Pawn(Piece.BLACK);
            pawn.setCoordinate(i, j);
            tileMatrix[i][j].setPiece(pawn);
        }

        if (i == 0) {
            if (j == 0 || j == 7) {
                url = "com/visual/chess/images/chessPiecesImages/BlackRook.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                Rook rook = new Rook(Piece.BLACK);
                rook.setCoordinate(i, j);
                tileMatrix[i][j].setPiece(rook);
            }

            if (j == 1 || j == 6) {
                url = "com/visual/chess/images/chessPiecesImages/BlackKnight.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                Knight knight = new Knight(Piece.BLACK);
                knight.setCoordinate(i, j);
                tileMatrix[i][j].setPiece(knight);
            }

            if (j == 2 || j == 5) {
                url = "com/visual/chess/images/chessPiecesImages/BlackBishop.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                Bishop bishop = new Bishop(Piece.BLACK);
                bishop.setCoordinate(i, j);
                tileMatrix[i][j].setPiece(bishop);
            }

            if (j == 3) {
                url = "com/visual/chess/images/chessPiecesImages/BlackQueen.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                Queen queen = new Queen(Piece.BLACK);
                queen.setCoordinate(i, j);
                tileMatrix[i][j].setPiece(queen);
            }

            if (j == 4) {
                url = "com/visual/chess/images/chessPiecesImages/BlackKing.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                King king = new King(Piece.BLACK);
                king.setCoordinate(i, j);
                tileMatrix[i][j].setPiece(king);
            }
        }
    }

    public Button createNewButton(int i, int j) {
        Button button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onButtonClick(event);
            }
        });
        button.setPrefSize(100, 100);
        if ((i + j) % 2 == 0)
            button.setStyle("-fx-background-color: #EBECD0");
        else
            button.setStyle("-fx-background-color: #779556");

        return button;
    }

    public Button addPieces(Button button, String url) {
        Image img = new Image(url);
        ImageView view = new ImageView(img);
        view.setFitHeight(70);
        view.setFitWidth(70);
        button.setGraphic(view);

        return button;
    }

    public Button addPossibleMoves(Button button) {
        Image img = new Image("com/visual/chess/images/chessPiecesImages/PathDot.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        button.setGraphic(view);
        button.setId("possibleMove");

        return button;
    }

    private int toIndex(Integer value) {
        return value == null ? 0 : value;
    }

    public void onButtonClick(ActionEvent event) {
        getCoordinates(event, chessBoard);
    }

    public void getCoordinates(ActionEvent event, GridPane chessBoard) {
        if(outOfMaterial())
            return;
        Node node = (Node) event.getSource();
        if (sourceToTarget == 0) {
            currentNode = node;
            currentRow = toIndex(GridPane.getRowIndex(node));
            currentColumn = toIndex(GridPane.getColumnIndex(node));

            System.out.println("first click: " + tileMatrix[currentRow][currentColumn].getPiece());

            if(tileMatrix[currentRow][currentColumn].getPiece() != null) {
                if (tileMatrix[currentRow][currentColumn].getPiece().getColor() == pieceColor) {
                    if (getTile().getPiece() != null) {
                        showPath(getTile().getPiece());
                        sourceToTarget = 1;
                    }
                }
            }

        } else {
            targetNode = node;
            targetRow = toIndex(GridPane.getRowIndex(node));
            targetColumn = toIndex(GridPane.getColumnIndex(node));

            if(currentRow == targetRow && currentColumn == targetColumn){
                sourceToTarget = 0;
                removePath();
                return;
            }

            Coordinate destination = new Coordinate(targetRow, targetColumn);

            getDeltaCoordinates();
            if (targetNode != currentNode) {
                removePath();
                if (getTile().getPiece() != null) {
                    if (getTile().getPiece().canMove(destination)) {
                        System.out.println("last click: " + tileMatrix[targetRow][targetColumn].getPiece());

                        boolean hasEaten = false;
                        boolean isPawn = false;

                        if(tileMatrix[destination.getRow()][destination.getColumn()].getPiece() != null){
                            hasEaten = true;
                        }

                        if(tileMatrix[currentRow][currentColumn].getPiece() instanceof Pawn){
                            isPawn = true;
                        }

                        move(destination);

                        if(hasEaten || isPawn){
                            fiftyMoves = 4;
                        }else{
                            fiftyMoves--;
                            if(fiftyMoves == 0){
                                gameFinished = true;
                                fiftyMovesPopUp();
                            }
                        }

                        if(outOfMaterial()) {
                            gameFinished = true;
                            outOfMaterialPopUp();
                        }

                        if(pieceColor == Piece.WHITE){
                            playerNameLabel.setText(PlayersNameController.blackPiecePlayer);
                            pieceColor = Piece.BLACK;
                        }else{
                            pieceColor = Piece.WHITE;
                            playerNameLabel.setText(PlayersNameController.whitePiecePlayer);
                        }

                        if(isCheck(pieceColor)){
                            if(!isValidMovement(pieceColor)){
                                gameFinished = true;
                                checkMatePopUp();
                            }
                        }else{
                            if(!isValidMovement(pieceColor)){
                                gameFinished = true;
                                stalematePopUp();
                            }
                        }
                    }
                    sourceToTarget = 0;
                } else {
                    sourceToTarget = 0;
                    removePath();
                }
            }
        }
    }

    public void move(Coordinate destination) {
        int targetRow = destination.getRow();
        int targetColumn = destination.getColumn();

        Tile tile = new Tile();
        tileMatrix[targetRow][targetColumn] = tileMatrix[currentRow][currentColumn];
        tileMatrix[targetRow][targetColumn].getPiece().setCoordinate(targetRow, targetColumn);
        tileMatrix[targetRow][targetColumn].getPiece().setWasMoved(true);
        tileMatrix[currentRow][currentColumn] = tile;

        Button button = buttonMatrix[targetRow][targetColumn];
        button.setGraphic(buttonMatrix[currentRow][currentColumn].getGraphic());
        buttonMatrix[currentRow][currentColumn].setGraphic(null);
    }

    public void getDeltaCoordinates() {
        changeXPosition = targetRow - currentRow;
        changeYPosition = targetColumn - currentColumn;
    }

    public Tile getTile() {
        return tileMatrix[currentRow][currentColumn];
    }

    public void showPath(Piece piece) {
        piece = piece.clonePiece();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinate destination = new Coordinate(i, j);
                Button button = buttonMatrix[destination.getRow()][destination.getColumn()];
                if (piece.canEat(destination)) {
                    button.setId("possibleEatMove");
                    button.getStyleClass().add("eatBlock");
                } else if (piece.canMove(destination)) {
                    button = addPossibleMoves(button);
                    button.setId("possibleMove");
                }
            }
        }
    }

    public void removePath() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = buttonMatrix[i][j];
                if (button.getId() == null)
                    continue;

                if (button.getId().equals("possibleEatMove")) {
                    button.getStyleClass().remove("eatBlock");
                } else if (button.getId().equals("possibleMove")) {
                    button.setGraphic(null);
                }

                button.setId(null);
            }
        }
    }

    private static Coordinate getKingPosition(int player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = tileMatrix[i][j].getPiece();
                if (piece instanceof King && piece.getColor() == player)
                    return piece.getCoordinate();
            }
        }
        return null;
    }

    public static boolean isCheck(int player) {
        Coordinate kingCoordinate = getKingPosition(player);
        if(kingCoordinate == null)
            return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = tileMatrix[i][j].getPiece();
                if (piece == null || piece.getColor() == player)
                    continue;

                if (piece.canEat(kingCoordinate))
                    return true;
            }
        }
        return false;
    }

    public boolean isValidMovement(int player) {
        int pieceBlockAmount = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(tileMatrix[i][j].getPiece() != null) {
                    if(tileMatrix[i][j].getPiece().getColor() == player) {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                Coordinate destination = new Coordinate(k,l);
                                if(tileMatrix[i][j].getPiece().canMove(destination)){
                                    pieceBlockAmount++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return pieceBlockAmount != 0;
    }

    @FXML
    public void onResetButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/resetPopUp.fxml"));
            Parent parent = fxmlLoader.load();
            ResetPopUpController resetPopUpController = fxmlLoader.getController();
            resetPopUpController.setConfirmationListener(new ConfirmationListener() {
                @Override
                public void onYesClick() {
                    PlayersNameController.boardStage.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/playersName-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

                        Stage chessBoardStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                        chessBoardStage.setResizable(false);
                        chessBoardStage.setTitle("VP Chess");
                        chessBoardStage.setScene(scene);
                        chessBoardStage.centerOnScreen();
                        chessBoardStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Scene alertPopUp = new Scene(parent, 400, 300);

            Image alertIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/alert.png")));
            stage.getIcons().add(alertIcon);

            stage.setResizable(false);
            stage.setTitle("ALERT");
            stage.setScene(alertPopUp);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void onDrawButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/drawPopUp.fxml"));
            Parent parent = fxmlLoader.load();
            DrawPopUpController drawPopUpController = fxmlLoader.getController();
            drawPopUpController.setConfirmationListener(new ConfirmationListener() {
                @Override
                public void onYesClick() {
                    //DrawScreenController.boardStageSecond.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/drawScreen.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

                        Stage drawStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                        drawStage.setTitle("VP Chess");
                        drawStage.setResizable(false);
                        drawStage.setScene(scene);
                        drawStage.centerOnScreen();
                        drawStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Scene alertPopUp = new Scene(parent, 400, 300);

            Image alertIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/alert.png")));
            stage.getIcons().add(alertIcon);

            stage.setResizable(false);
            stage.setTitle("ALERT");
            stage.setScene(alertPopUp);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @FXML
    public void onSurrenderButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/surrenderPopUp.fxml"));
            Parent parent = fxmlLoader.load();
            DrawPopUpController drawPopUpController = fxmlLoader.getController();
            drawPopUpController.setConfirmationListener(new ConfirmationListener() {
                @Override
                public void onYesClick() {
                    //DrawScreenController.boardStageSecond.close();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/surrenderScreen.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

                        Stage drawStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                        drawStage.setTitle("VP Chess");
                        drawStage.setResizable(false);
                        drawStage.setScene(scene);
                        drawStage.centerOnScreen();
                        drawStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Scene alertPopUp = new Scene(parent, 400, 300);

            Image alertIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/alert.png")));
            stage.getIcons().add(alertIcon);

            stage.setResizable(false);
            stage.setTitle("ALERT");
            stage.setScene(alertPopUp);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean outOfMaterial(){
        int blackPawns = 0, whitePawns = 0;
        int blackRook = 0, whiteRook = 0;
        int blackBishop = 0, whiteBishop = 0;
        int blackKnight = 0, whiteKnight = 0;
        int queens = 0, kings = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(tileMatrix[i][j].getPiece() == null)
                    continue;

                if(tileMatrix[i][j].getPiece() instanceof King){
                    kings++;
                }

                if(tileMatrix[i][j].getPiece() instanceof Queen){
                    queens++;
                }

                if(tileMatrix[i][j].getPiece() instanceof Bishop){
                    if(tileMatrix[i][j].getPiece().getColor() == Piece.WHITE)  {
                        whiteBishop++;
                    }else{
                        blackBishop++;
                    }
                }

                if(tileMatrix[i][j].getPiece() instanceof Rook){
                    if(tileMatrix[i][j].getPiece().getColor() == Piece.WHITE)  {
                        whiteRook++;
                    }else{
                        blackRook++;
                    }
                }

                if(tileMatrix[i][j].getPiece() instanceof Knight){
                    if(tileMatrix[i][j].getPiece().getColor() == Piece.WHITE)  {
                        whiteKnight++;
                    }else{
                        blackKnight++;
                    }
                }

                if(tileMatrix[i][j].getPiece() instanceof Pawn){
                    if(tileMatrix[i][j].getPiece().getColor() == Piece.WHITE)  {
                        whitePawns++;
                    }else{
                        blackPawns++;
                    }
                }
            }
        }

        int sumOfKnights = blackKnight + whiteKnight;
        int sumOfRooks = blackRook + whiteRook;
        int sumOfPawns = blackPawns + whitePawns;
        int sumOfBishop = blackBishop + whiteBishop;

        if(kings != 0 && sumOfBishop == 0 && sumOfKnights == 0 && sumOfPawns == 0 && sumOfRooks == 0 && queens == 0)
            return true;

        if(kings != 0 && sumOfKnights == 0 && sumOfPawns == 0 && sumOfRooks == 0 && queens == 0 &&
                ((blackBishop == 1 && whiteBishop == 0) || (blackBishop == 0 && whiteBishop == 1)))
            return true;

        if(kings != 0 && sumOfBishop == 0 && sumOfPawns == 0 && sumOfRooks == 0 && queens == 0 &&
                ((blackKnight == 1 && whiteKnight == 0) || (blackKnight == 0 && whiteKnight == 1)))
            return true;

        return false;
    }

    public void checkMatePopUp(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/checkmatePopUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            Image chessIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/icon.png")));

            stage.getIcons().add(chessIcon);
            stage.setResizable(false);
            stage.setTitle("VP Chess");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> stage.close());
            delay.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void stalematePopUp(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/stalematePopUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            Image chessIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/icon.png")));

            stage.getIcons().add(chessIcon);
            stage.setResizable(false);
            stage.setTitle("VP Chess");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> stage.close());
            delay.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void fiftyMovesPopUp(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/fiftyMovesPopUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            Image chessIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/icon.png")));

            stage.getIcons().add(chessIcon);
            stage.setResizable(false);
            stage.setTitle("VP Chess");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> stage.close());
            delay.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void outOfMaterialPopUp(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("views/outOfMaterialPopUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            Image chessIcon = new Image(Objects.requireNonNull(ChessApplication.class.getResourceAsStream("images/icon.png")));

            stage.getIcons().add(chessIcon);
            stage.setResizable(false);
            stage.setTitle("VP Chess");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> stage.close());
            delay.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}