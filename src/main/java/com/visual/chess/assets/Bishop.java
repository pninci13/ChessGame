package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;

public class Bishop extends Piece {

    public Bishop(int color) {
        super(color);
    }

    @Override
    public boolean canMove(Coordinate destination) {
        int bishopCurrentRow, bishopCurrentColumn;
        int bishopTargetRow = destination.getRow();
        int bishopTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        bishopCurrentRow = source.getRow();
        bishopCurrentColumn = source.getColumn();

        int changeXPosition = bishopTargetRow - bishopCurrentRow;
        int changeYPosition = bishopTargetColumn - bishopCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (source.equals(destination)) {
            return false;
        }

        boolean valid = false;

        if ((Math.abs(changeXPosition) == Math.abs(changeYPosition))) {
            if (changeXPosition > 0 && changeYPosition > 0) {
                for (int i = bishopCurrentRow + 1, j = bishopCurrentColumn + 1; (i < bishopTargetRow) && (j < bishopTargetColumn); i++, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }

            }

            if (changeXPosition > 0 && changeYPosition < 0) {
                for (int i = bishopCurrentRow + 1, j = bishopCurrentColumn - 1; (i < bishopTargetRow) && (j > bishopTargetColumn); i++, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        valid = true;

                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition > 0) {
                for (int i = bishopCurrentRow - 1, j = bishopCurrentColumn + 1; (i > bishopTargetRow) && (j < bishopTargetColumn); i--, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        valid = true;

                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition < 0) {

                for (int i = bishopCurrentRow - 1, j = bishopCurrentColumn - 1; (i > bishopTargetRow) && (j > bishopTargetColumn); i--, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        valid = true;

                    } else {
                        return false;
                    }
                }
            }
            valid = true;
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
        int bishopTargetRow = destination.getRow();
        int bishopTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        int bishopCurrentRow = source.getRow();
        int bishopCurrentColumn = source.getColumn();

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (!canMoveToEat(destination)) {
            return false;
        }

        if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null && tiles[bishopCurrentRow][bishopCurrentColumn].getPiece() != null) {
            return tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor();
        }

        return false;
    }

    public boolean canMoveToEat(Coordinate destination) {
        int bishopCurrentRow, bishopCurrentColumn;
        int bishopTargetRow = destination.getRow();
        int bishopTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        bishopCurrentRow = source.getRow();
        bishopCurrentColumn = source.getColumn();

        int changeXPosition = bishopTargetRow - bishopCurrentRow;
        int changeYPosition = bishopTargetColumn - bishopCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (source.equals(destination)) {
            return false;
        }

        if ((Math.abs(changeXPosition) == Math.abs(changeYPosition))) {
            if (changeXPosition > 0 && changeYPosition > 0) {
                for (int i = bishopCurrentRow + 1, j = bishopCurrentColumn + 1; (i < bishopTargetRow) && (j < bishopTargetColumn); i++, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }

            }

            if (changeXPosition > 0 && changeYPosition < 0) {
                for (int i = bishopCurrentRow + 1, j = bishopCurrentColumn - 1; (i < bishopTargetRow) && (j > bishopTargetColumn); i++, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition > 0) {
                for (int i = bishopCurrentRow - 1, j = bishopCurrentColumn + 1; (i > bishopTargetRow) && (j < bishopTargetColumn); i--, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition < 0) {

                for (int i = bishopCurrentRow - 1, j = bishopCurrentColumn - 1; (i > bishopTargetRow) && (j > bishopTargetColumn); i--, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[bishopTargetRow][bishopTargetColumn].getPiece() != null) {
                    if (tiles[bishopCurrentRow][bishopCurrentColumn].getPiece().getColor() != tiles[bishopTargetRow][bishopTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}