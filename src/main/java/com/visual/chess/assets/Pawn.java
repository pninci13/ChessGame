package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;
import com.visual.chess.assets.Tile;

public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    public boolean canMove(Coordinate destination) {
        int pawnTargetRow = destination.getRow();
        int pawnTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        int pawnCurrentRow = source.getRow();
        int pawnCurrentColumn = source.getColumn();

        int changeXPosition = pawnTargetRow - pawnCurrentRow;
        int changeYPosition = pawnTargetColumn - pawnCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;


        if (source.equals(destination)) {
            return false;
        }

        int i = 1;

        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color == Piece.WHITE) {
            i = -1;
        }

        boolean backwardMovement = (pawnCurrentRow + (-i) == pawnTargetRow);

        if (!wasMoved()) {
            if (!backwardMovement) {
                if (canEat(destination)) {
                    return true;
                }

                if ((Math.abs(changeXPosition) <= 2 && changeYPosition == 0) && (tiles[pawnCurrentRow + 2 * i][pawnCurrentColumn].getPiece() == null)) {
                    if (tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null) {
                        return true;
                    }
                } else if (tiles[pawnCurrentRow + 2 * i][pawnCurrentColumn].getPiece() != null) {
                    if ((Math.abs(changeXPosition) <= 1 && changeYPosition == 0) && (tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null)) {
                        return true;
                    }

                    return false;
                }


//                if ((Math.abs(changeXPosition) <= 2 && changeYPosition == 0) && (tiles[pawnCurrentRow + 2 * i][pawnCurrentColumn].getPiece() == null)) {
//                    return true;
//
//                } else if ((Math.abs(changeXPosition) <= 1 && changeYPosition == 0) && tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null) {
//                    return true;
//
//                }

            }
            return false;

        } else {
            if (!backwardMovement) {
                if (canEat(destination)) {
                    return true;
                }

                if ((Math.abs(changeXPosition) <= 1 && changeYPosition == 0) && tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canEat(Coordinate destination) {
        int pawnTargetRow = destination.getRow();
        int pawnTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        int pawnCurrentRow = source.getRow();
        int pawnCurrentColumn = source.getColumn();

        int changeXPosition = pawnTargetRow - pawnCurrentRow;
        int changeYPosition = pawnTargetColumn - pawnCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        int i = 1;

        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color == Piece.WHITE) {
            i = -1;
        }

        if (Math.abs(changeXPosition) == 1 && ((changeYPosition == 1) || (changeYPosition == -1))) {
            if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)) {
                if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color != tiles[pawnTargetRow][pawnTargetColumn].getPiece().color) {
                    if (tiles[pawnCurrentRow + i][pawnCurrentColumn + i].getPiece() != null) {
                        return true;
                    } else if (tiles[pawnCurrentRow + i][pawnCurrentColumn - i].getPiece() != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}