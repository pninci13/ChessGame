package com.visual.chess.assets;
import com.visual.chess.controllers.ChessBoardController;
import java.util.ArrayList;
import static com.visual.chess.controllers.ChessBoardController.tileMatrix;

public abstract class Piece {
    int color;
    public static final int BLACK = 1;
    public static final int WHITE = 0;
    private Coordinate coordinate;
    private boolean wasMoved = false;

    private ArrayList<Coordinate> nextMoves = new ArrayList<>();
    private ArrayList<Coordinate> getThreatMoves = new ArrayList<>();

    public Piece(int color) {
        this.color = color;
    }

    public static void boardLog(Tile[][] board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece == null){
                    System.out.print("    -    ");
                    continue;
                }
                String pieceLetter = "";
                if (piece instanceof Pawn)
                    pieceLetter = "P";
                else if (piece instanceof Rook)
                    pieceLetter = "R";
                else if (piece instanceof Bishop)
                    pieceLetter = "B";
                else if (piece instanceof Knight)
                    pieceLetter = "K";
                else if (piece instanceof King)
                    pieceLetter = "+";
                else if (piece instanceof Queen)
                    pieceLetter = "Q";
                System.out.print(pieceLetter+ piece.getCoordinate().getRow() + piece.getCoordinate().getColumn() + piece.wasMoved() + " ");
            }
            System.out.println("");
        }
    }

    public boolean canBlockCheck(Coordinate destination){
        System.out.println("----- Tile Matrix -----");
        boardLog(tileMatrix);
        System.out.println("");
        System.out.println("");
        Tile[][] currentBoard = Tile.cloneFrom(tileMatrix);
        System.out.println("----- Current Board -----");
        boardLog(currentBoard);

        int sourceRow = getCoordinate().getRow();
        int sourceColumn = getCoordinate().getColumn();

        Piece piece = tileMatrix[sourceRow][sourceColumn].getPiece();
        tileMatrix[destination.getRow()][destination.getColumn()].setPiece(piece);

        piece.setCoordinate(destination.getRow(), destination.getColumn());
        piece.setWasMoved(true);
        tileMatrix[sourceRow][sourceColumn].setPiece(null);

//        System.out.println("APOS MUDANCA");
//        boardLog(tileMatrix);

        boolean isCheck = ChessBoardController.isCheck(getColor());

        tileMatrix = currentBoard;
//        System.out.println("\nAPOS RECOVERY");
//        boardLog(tileMatrix); //TODO essa board log pode ser util mantenha salvo

        return !isCheck;
    }

    public void setGetThreatMoves(ArrayList<Coordinate> getThreatMoves) {
        this.getThreatMoves = getThreatMoves;
    }

    public boolean wasMoved() {
        return wasMoved;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int row, int col) {
        this.coordinate = new Coordinate(row, col);
    }

    public Piece clonePiece() {
        Piece piece = null;
        if (this instanceof Pawn)
            piece = new Pawn(getColor());
        else if (this instanceof Rook)
            piece = new Rook(getColor());
        else if (this instanceof Bishop)
            piece = new Bishop(getColor());
        else if (this instanceof Knight)
            piece = new Knight(getColor());
        else if (this instanceof King)
            piece = new King(getColor());
        else if (this instanceof Queen)
            piece = new Queen(getColor());

        if (piece != null) {
            piece.setCoordinate(getCoordinate().getRow(), getCoordinate().getColumn());
            piece.setWasMoved(wasMoved());
        }

        return piece;
    }

    public abstract boolean canMove(Coordinate destination);

    public abstract boolean canMoveToBlock(Coordinate destination);

    public abstract boolean canEat(Coordinate destination);

    public ArrayList<Coordinate> getNextMoves() {
        return nextMoves;
    }
}