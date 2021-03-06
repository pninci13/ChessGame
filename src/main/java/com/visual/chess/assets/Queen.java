package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
    }

    @Override
    public boolean canMove(Coordinate destination) {
        int queenTargetRow = destination.getRow();
        int queenTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        int queenCurrentRow = source.getRow();
        int queenCurrentColumn = source.getColumn();

        int changeXPosition = queenTargetRow - queenCurrentRow;
        int changeYPosition = queenTargetColumn - queenCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (source.equals(destination)) {
            return false;
        }

        boolean valid = false;

        if (queenCurrentRow == queenTargetRow) {
            if (changeYPosition > 0) {
                for (int i = queenCurrentColumn + 1; i < queenTargetColumn; i++) {
                    if (tiles[queenCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }

            if (changeYPosition < 0) {

                for (int i = queenCurrentColumn - 1; i > queenTargetColumn; i--) {
                    if (tiles[queenCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }
        }

        if (queenCurrentColumn == queenTargetColumn) {
            if (changeXPosition > 0) {
                for (int i = queenCurrentRow + 1; i < queenTargetRow; i++) {
                    if (tiles[i][queenCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }

            if (changeXPosition < 0) {
                for (int i = queenCurrentRow - 1; i > queenTargetRow; i--) {
                    if (tiles[i][queenCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }
        }

        if ((Math.abs(changeXPosition) == Math.abs(changeYPosition))) {
            if (changeXPosition > 0 && changeYPosition > 0) {
                for (int i = queenCurrentRow + 1, j = queenCurrentColumn + 1; (i < queenTargetRow) && (j < queenTargetColumn); i++, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition > 0 && changeYPosition < 0) {
                for (int i = queenCurrentRow + 1, j = queenCurrentColumn - 1; (i < queenTargetRow) && (j > queenTargetColumn); i++, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition > 0) {
                for (int i = queenCurrentRow - 1, j = queenCurrentColumn + 1; (i > queenTargetRow) && (j < queenTargetColumn); i--, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition < 0) {
                for (int i = queenCurrentRow - 1, j = queenCurrentColumn - 1; (i > queenTargetRow) && (j > queenTargetColumn); i--, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
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
        int queenTargetRow = destination.getRow();
        int queenTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        int queenCurrentRow = source.getRow();
        int queenCurrentColumn = source.getColumn();

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (!canMoveToEat(destination))
            return false;

        if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null && tiles[queenCurrentRow][queenCurrentColumn].getPiece() != null) {
            return tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor();
        }

        return false;
    }

    public boolean canMoveToEat(Coordinate destination) {
        int queenTargetRow = destination.getRow();
        int queenTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        int queenCurrentRow = source.getRow();
        int queenCurrentColumn = source.getColumn();

        int changeXPosition = queenTargetRow - queenCurrentRow;
        int changeYPosition = queenTargetColumn - queenCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (source.equals(destination)) {
            return false;
        }

        if (queenCurrentRow == queenTargetRow) {
            if (changeYPosition > 0) {
                for (int i = queenCurrentColumn + 1; i < queenTargetColumn; i++) {
                    if (tiles[queenCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }

            if (changeYPosition < 0) {

                for (int i = queenCurrentColumn - 1; i > queenTargetColumn; i--) {
                    if (tiles[queenCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }

        if (queenCurrentColumn == queenTargetColumn) {
            if (changeXPosition > 0) {
                for (int i = queenCurrentRow + 1; i < queenTargetRow; i++) {
                    if (tiles[i][queenCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }

            if (changeXPosition < 0) {
                for (int i = queenCurrentRow - 1; i > queenTargetRow; i--) {
                    if (tiles[i][queenCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;

                    } else {
                        return false;
                    }
                }
                return true;
            }
        }

        if ((Math.abs(changeXPosition) == Math.abs(changeYPosition))) {
            if (changeXPosition > 0 && changeYPosition > 0) {
                for (int i = queenCurrentRow + 1, j = queenCurrentColumn + 1; (i < queenTargetRow) && (j < queenTargetColumn); i++, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;

                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition > 0 && changeYPosition < 0) {
                for (int i = queenCurrentRow + 1, j = queenCurrentColumn - 1; (i < queenTargetRow) && (j > queenTargetColumn); i++, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;

                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition > 0) {
                for (int i = queenCurrentRow - 1, j = queenCurrentColumn + 1; (i > queenTargetRow) && (j < queenTargetColumn); i--, j++) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
                        return true;

                    } else {
                        return false;
                    }
                }
            }

            if (changeXPosition < 0 && changeYPosition < 0) {
                for (int i = queenCurrentRow - 1, j = queenCurrentColumn - 1; (i > queenTargetRow) && (j > queenTargetColumn); i--, j--) {
                    if (tiles[i][j].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[queenTargetRow][queenTargetColumn].getPiece() != null) {
                    if (tiles[queenCurrentRow][queenCurrentColumn].getPiece().getColor() != tiles[queenTargetRow][queenTargetColumn].getPiece().getColor()) {
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