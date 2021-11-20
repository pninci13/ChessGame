package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;

public class King extends Piece {
    public boolean wasMoved = false;

    public King(int color) {
        super(color);
    }

    @Override
    public boolean canMove(Coordinate destination) {
        int kingCurrentRow, kingCurrentColumn;
        int kingTargetRow = destination.getRow();
        int kingTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        kingCurrentRow = source.getRow();
        kingCurrentColumn = source.getColumn();

        int changeXPosition = kingTargetRow - kingCurrentRow;
        int changeYPosition = kingTargetColumn - kingCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if ((Math.abs(changeXPosition) == 1 && Math.abs(changeYPosition) == 0) ||
        (Math.abs(changeXPosition) == 0 && Math.abs(changeYPosition) == 1) ||
        (Math.abs(changeXPosition) == 1 && Math.abs(changeYPosition) == 1)){
            if (tiles[kingTargetRow][kingTargetColumn].getPiece() != null) {
                if (tiles[kingCurrentRow][kingCurrentColumn].getPiece().getColor() != tiles[kingTargetRow][kingTargetColumn].getPiece().getColor()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canEat(Coordinate destination) {
        int kingCurrentRow, kingCurrentColumn;
        int kingTargetRow = destination.getRow();
        int kingTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        kingCurrentRow = source.getRow();
        kingCurrentColumn = source.getColumn();

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (tiles[kingTargetRow][kingTargetColumn].getPiece() != null) {
            if (tiles[kingCurrentRow][kingCurrentColumn].getPiece().getColor() != tiles[kingTargetRow][kingTargetColumn].getPiece().getColor())
                return true;
        }

        return false;
    }
}