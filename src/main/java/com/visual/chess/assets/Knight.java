package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;

public class Knight extends Piece {
    public Knight(int color) {
        super(color);
    }


    @Override
    public boolean canMove(Coordinate destination) {
        int knightCurrentRow, knightCurrentColumn;
        int knightTargetRow = destination.getRow();
        int knightTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        knightCurrentRow = source.getRow();
        knightCurrentColumn = source.getColumn();

        Tile[][] tiles = ChessBoardController.tileMatrix;

        int[] XAxis = {-2, -1, +1, +2, +2, +1, -1, -2};
        int[] YAxis = {+1, +2, +2, +1, -1, -2, -2, -1};

        int knightXMove, knightYMove;
        boolean valid = false;

        for (int i = 0; i < 8; i++) {
            knightXMove = knightCurrentRow + XAxis[i];
            knightYMove = knightCurrentColumn + YAxis[i];

            if ((knightXMove == knightTargetRow) && (knightYMove == knightTargetColumn)) {
                if (tiles[knightTargetRow][knightTargetColumn].getPiece() != null) {
                    if (tiles[knightTargetRow][knightTargetColumn].getPiece().getColor() != tiles[knightCurrentRow][knightCurrentColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }
        }

        if (valid) {
            if (ChessBoardController.isCheck(getColor())) {
                boolean cb = canBlockCheck(destination);
                return cb;
            }

            if (moveWillThreatKing(destination)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveToBlock(Coordinate destination) {
        return false;
    }

    @Override
    public boolean canEat(Coordinate destination) {
        int knightCurrentRow, knightCurrentColumn;
        int knightTargetRow = destination.getRow();
        int knightTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        knightCurrentRow = source.getRow();
        knightCurrentColumn = source.getColumn();

        int changeXPosition = knightTargetRow - knightCurrentRow;
        int changeYPosition = knightTargetColumn - knightCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (!canMoveToEat(destination)) {
            return false;
        }

        if (tiles[knightTargetRow][knightTargetColumn].getPiece() != null && tiles[knightCurrentRow][knightCurrentColumn].getPiece() != null) {
            if (tiles[knightTargetRow][knightTargetColumn].getPiece().getColor() != tiles[knightCurrentRow][knightCurrentColumn].getPiece().getColor()) {
                return true;
            }
        }
        return false;
    }

    public boolean canMoveToEat(Coordinate destination) {
        int knightCurrentRow, knightCurrentColumn;
        int knightTargetRow = destination.getRow();
        int knightTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        knightCurrentRow = source.getRow();
        knightCurrentColumn = source.getColumn();

        Tile[][] tiles = ChessBoardController.tileMatrix;

        int[] XAxis = {-2, -1, +1, +2, +2, +1, -1, -2};
        int[] YAxis = {+1, +2, +2, +1, -1, -2, -2, -1};

        int knightXMove, knightYMove;
        boolean valid = false;

        for (int i = 0; i < 8; i++) {
            knightXMove = knightCurrentRow + XAxis[i];
            knightYMove = knightCurrentColumn + YAxis[i];

            if ((knightXMove == knightTargetRow) && (knightYMove == knightTargetColumn)) {
                if (tiles[knightTargetRow][knightTargetColumn].getPiece() != null) {
                    if (tiles[knightTargetRow][knightTargetColumn].getPiece().getColor() != tiles[knightCurrentRow][knightCurrentColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}