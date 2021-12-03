package com.visual.chess.controllers;

import com.visual.chess.ChessApplication;
import com.visual.chess.ConfirmationListener;
import com.visual.chess.assets.*;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class ChessBoardController {

    public int currentRow, currentColumn;
    public static int targetRow, targetColumn, sourceToTarget;
    public static int changeXPosition, changeYPosition;
    public Label vezDeLabel;
    public static int fiftyMoves = 0, piecesAmount = 0;

    @FXML
    private Button surrenderButton;

    @FXML
    private Button drawButton;

    @FXML
    private Button resetButton;

    Node currentNode, targetNode;

    static Button[][] buttonMatrix = new Button[8][8];
    public static Tile[][] tileMatrix = new Tile[8][8];

    @FXML
    public GridPane chessBoard;

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
//        move(currentNode, targetNode);

    }


    public void getCoordinates(ActionEvent event, GridPane chessBoard) {

        Node node = (Node) event.getSource();
        if (sourceToTarget == 0) {
            currentNode = node;
            currentRow = toIndex(GridPane.getRowIndex(node));
            currentColumn = toIndex(GridPane.getColumnIndex(node));

            System.out.println("first click: " + tileMatrix[currentRow][currentColumn].getPiece());

            Piece.boardLog(tileMatrix);
            if (getTile().getPiece() != null) {
                showPath(getTile().getPiece());

                sourceToTarget = 1;
            }//else
//                System.out.println("puece null");

        } else {
            targetNode = node;
            targetRow = toIndex(GridPane.getRowIndex(node));
            targetColumn = toIndex(GridPane.getColumnIndex(node));

            Coordinate destination = new Coordinate(targetRow, targetColumn);

            getDeltaCoordinates();
            if (targetNode != currentNode) {
                removePath();
                if (getTile().getPiece() != null) {
                    if (getTile().getPiece().canMove(destination)) {
                        System.out.println("last click: " + tileMatrix[targetRow][targetColumn].getPiece());
                        move(destination);

                        fiftyMoves = fiftyMoves + 1;

                        if (fiftyMoves == 50) {
//                            System.out.println("empate por 50 movimentos");
                        }
//                        System.out.println(fiftyMoves + " - Eggggggggggggggggg");
                        //  allNextMoves();
//                    }
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
        //Evitar sobreescrita na variavel quando canBlockCheck mover a peça
        boolean trueState = piece.wasMoved();
//        System.out.println("peca antes: " + " -> (" + piece.getCoordinate().getRow() + ", " + piece.getCoordinate().getColumn() + ")  -> " + trueState);
        piece = piece.clonePiece();
        piece.setWasMoved(trueState);
//            piece.setWasMoved(true);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinate destination = new Coordinate(i, j);
                Button button = buttonMatrix[destination.getRow()][destination.getColumn()];
                if (piece.canEat(destination)) {
                    button.setId("possibleEatMove");
                    button.getStyleClass().add("eatBlock");
                } else if (piece.canMove(destination)) {
//                    System.out.println("peca depois: " + " -> (" + destination.getRow() + ", " + destination.getColumn() + ")  -> " + piece.wasMoved());
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

    /*public void showPlayerName(){
        PlayersNameController.whitePiecePlayer;
    }*/
}