package com.visual.chess.controllers;

import com.visual.chess.assets.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ChessBoardController {

    public static int currentRow, currentColumn;
    public static int targetRow, targetColumn, sourceToTarget;
    int changeXPosition, changeYPosition;
    Node currentNode, targetNode;

    static Button[][] buttonMatrix = new Button[8][8];
    public static Tile[][] tileMatrix = new Tile[8][8];

    //matriz de imageView

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
    }

    public void whitePieces(int i, int j) {
        String url;

        if (i == 6) {
            url = "com/visual/chess/images/chessPiecesImages/WhitePawn.png";
            Button button = createNewButton(i, j);
            button = addPieces(button, url);
            chessBoard.add(button, j, 6);
            buttonMatrix[6][j] = button;
            tileMatrix[6][j].setPiece(new Pawn(Piece.WHITE));
        }

        if (i == 7) {
            if (j == 0 || j == 7) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteRook.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                tileMatrix[7][j].setPiece(new Rook(Piece.WHITE));
            }

            if (j == 1 || j == 6) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteKnight.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                tileMatrix[7][j].setPiece(new Knight(Piece.WHITE));
            }

            if (j == 2 || j == 5) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteBishop.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                tileMatrix[7][j].setPiece(new Bishop(Piece.WHITE));
            }

            if (j == 3) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteQueen.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                tileMatrix[7][j].setPiece(new Queen(Piece.WHITE));
            }

            if (j == 4) {
                url = "com/visual/chess/images/chessPiecesImages/WhiteKing.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 7);
                buttonMatrix[7][j] = button;
                tileMatrix[7][j].setPiece(new King(Piece.WHITE));
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
            tileMatrix[1][j].setPiece(new Pawn(Piece.BLACK));
        }

        if (i == 0) {
            if (j == 0 || j == 7) {
                url = "com/visual/chess/images/chessPiecesImages/BlackRook.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                tileMatrix[0][j].setPiece(new Rook(Piece.BLACK));
            }

            if (j == 1 || j == 6) {
                url = "com/visual/chess/images/chessPiecesImages/BlackKnight.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                tileMatrix[0][j].setPiece(new Knight(Piece.BLACK));
            }

            if (j == 2 || j == 5) {
                url = "com/visual/chess/images/chessPiecesImages/BlackBishop.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                tileMatrix[0][j].setPiece(new Bishop(Piece.BLACK));
            }

            if (j == 3) {
                url = "com/visual/chess/images/chessPiecesImages/BlackQueen.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                tileMatrix[0][j].setPiece(new Queen(Piece.BLACK));
            }

            if (j == 4) {
                url = "com/visual/chess/images/chessPiecesImages/BlackKing.png";
                Button button = createNewButton(i, j);
                button = addPieces(button, url);
                chessBoard.add(button, j, 0);
                buttonMatrix[0][j] = button;
                tileMatrix[0][j].setPiece(new King(Piece.BLACK));
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
            System.out.println(tileMatrix[currentRow][currentColumn].getPiece());
            if (getTile().getPiece() != null) {
                showPath(getTile().getPiece());
                sourceToTarget = 1;
            }

        } else {
            targetNode = node;
            targetRow = toIndex(GridPane.getRowIndex(node));
            targetColumn = toIndex(GridPane.getColumnIndex(node));

            getDeltaCoordinates();
            if (targetNode != currentNode) {
                removePath();
                if (getTile().getPiece().canMove(changeXPosition, changeYPosition)) {
                    System.out.println(tileMatrix[currentRow][currentColumn].getPiece());
                    move();
                }

                sourceToTarget = 0;

            } else {
                sourceToTarget = 0;
                removePath();
            }

        }
    }

    public void move() {
//        chessBoard.getChildren().remove(targetNode);
//        chessBoard.getChildren().remove(currentNode);
//        chessBoard.add(currentNode, targetColumn, targetRow);

        Tile tile = new Tile();
        tileMatrix[targetRow][targetColumn] = tileMatrix[currentRow][currentColumn];
        tileMatrix[currentRow][currentColumn] = tile;

        Button button = createNewButton(currentRow, currentColumn);
        buttonMatrix[targetRow][targetColumn].setGraphic(buttonMatrix[currentRow][currentColumn].getGraphic());
        buttonMatrix[currentRow][currentColumn] = button;

        System.out.println("movido com sucesso");
        chessBoard.add(button, currentColumn, currentRow);
    }

    public void getDeltaCoordinates() {
        changeXPosition = targetRow - currentRow;
        changeYPosition = targetColumn - currentColumn;
    }

    public boolean isChessboardLimit(int value1, int value2) {
        if (value1 == -1 || value1 == 8 || value2 == -1 || value2 == 8) {
            return true;
        }

        return false;
    }

    public void showPath(Piece piece) {
//        int currentRow2 = currentRow;
//        int currentColumn2 = currentColumn;
//        int targetRow2 = targetRow;
//        int targetColumn2 = targetColumn;
//        Node node = currentNode;

        if (piece instanceof Pawn)
            showPawnPossibleMoves();

        if (piece instanceof Rook)
            showRookPossibleMoves();
    }

    public Tile getTile() {
        return tileMatrix[currentRow][currentColumn];
    }

    public void showPawnPossibleMoves() {
        int i = 1;

        if (getTile().getPiece().getColor() == Piece.WHITE) {
            i = -1;
        }

        int sum1 = currentRow + i;
        int sum2 = currentRow + 2 * i;

        if (isChessboardLimit(sum1, sum2)) {
            return;
        }

        if (getTile().getPiece() instanceof Pawn) {
//            if(tileMatrix[currentRow + i][currentColumn + i].getPiece() != null){
////                Button button = createNewButton(currentRow + i, currentColumn + i);
////                button.getStylesheets().add("/com/styles/styles.css");
////                button.setId("enemyFound");
//
////                buttonMatrix[currentRow + i][currentColumn + i] = button;
////                chessBoard.add(button, currentColumn + i, currentRow + i);
//                System.out.println("alou");
////                buttonMatrix[currentRow + i][currentColumn + i].setId("enemyFound");
////                buttonMatrix[currentRow + i][currentColumn + i].getStylesheets().add("/com/styles/styles.css");
//            }

//            if(tileMatrix[currentRow + i][currentColumn - i].getPiece() != null){
//                System.out.println("olar");
////                buttonMatrix[currentRow + i][currentColumn + i].getStylesheets().add("/com/styles/styles.css");
//            }

            if ((!((Pawn) getTile().getPiece()).wasMoved)) {
                if ((tileMatrix[currentRow + i][currentColumn].getPiece() == null) && (tileMatrix[currentRow + 2 * i][currentColumn].getPiece() == null)) {
                    Button button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow + i][currentColumn].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow + i][currentColumn].setId("possibleMove");

                    button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow + 2 * i][currentColumn].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow + 2 * i][currentColumn].setId("possibleMove");

                } else {
                    Button button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow + i][currentColumn].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow + i][currentColumn].setId("possibleMove");
                }
            } else if ((tileMatrix[currentRow + i][currentColumn].getPiece() == null)) {
                Button button = createNewButton(0, 0);
                button = addPossibleMoves(button);
                buttonMatrix[currentRow + i][currentColumn].setGraphic(button.getGraphic());
                buttonMatrix[currentRow + i][currentColumn].setId("possibleMove");
            }
        }

    }

    public void showRookPossibleMoves() {

        for (int i = 1; i < 8; i++) {
            if (!isChessboardLimit(currentRow, currentColumn + i)) {
                System.out.println("11111");
                System.out.println(tileMatrix[currentRow][currentColumn + i].getPiece());
                if (tileMatrix[currentRow][currentColumn + i].getPiece() == null) {
                    System.out.println("AAAAAA");
                    Button button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow][currentColumn + i].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow][currentColumn + i].setId("possibleMove");
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (!isChessboardLimit(currentRow, currentColumn - i)) {
                System.out.println("22222");
                System.out.println(tileMatrix[currentRow][currentColumn - i].getPiece());
                if (tileMatrix[currentRow][currentColumn - i].getPiece() == null) {
                    System.out.println("BBBBB");
                    Button button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow][currentColumn - i].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow][currentColumn - i].setId("possibleMove");
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (!isChessboardLimit(currentRow + i, currentColumn)) {
                System.out.println("33333");
                System.out.println(tileMatrix[currentRow + i][currentColumn].getPiece());
                if (tileMatrix[currentRow + i][currentColumn].getPiece() == null) {
                    System.out.println("CCCCC");
                    Button button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow + i][currentColumn].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow + i][currentColumn].setId("possibleMove");
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if (!isChessboardLimit(currentRow - i, currentColumn)) {
                System.out.println("44444");
                System.out.println(tileMatrix[currentRow - i][currentColumn].getPiece());
                if (tileMatrix[currentRow - i][currentColumn].getPiece() == null) {
                    System.out.println("DDDDD");
                    Button button = createNewButton(0, 0);
                    button = addPossibleMoves(button);
                    buttonMatrix[currentRow - i][currentColumn].setGraphic(button.getGraphic());
                    buttonMatrix[currentRow - i][currentColumn].setId("possibleMove");
                } else {
                    break;
                }
            } else {
                break;
            }
        }


    }

    public void removePath() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ("possibleMove".equals(buttonMatrix[i][j].getId())) {
                    buttonMatrix[i][j].setId(null);
                    buttonMatrix[i][j].setGraphic(null);
                }
            }
        }
    }

}