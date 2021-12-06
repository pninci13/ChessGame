package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;
import com.visual.chess.assets.Tile;

public class Rook extends Piece {

    public Rook(int color) {
        super(color);
    }

    @Override
    public boolean canMove(Coordinate destination) {
        int rookCurrentRow, rookCurrentColumn;
        int rookTargetRow = destination.getRow();
        int rookTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        rookCurrentRow = source.getRow();
        rookCurrentColumn = source.getColumn();

        int changeXPosition = rookTargetRow - rookCurrentRow;
        int changeYPosition = rookTargetColumn - rookCurrentColumn;

        boolean valid = false;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (rookCurrentRow == rookTargetRow) {
            if (changeYPosition > 0) {
                for (int i = rookCurrentColumn + 1; i < rookTargetColumn; i++) {
                    if (tiles[rookCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }

            if (changeYPosition < 0) {
                for (int i = rookCurrentColumn - 1; i > rookTargetColumn; i--) {
                    if (tiles[rookCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }
        }

        if (rookCurrentColumn == rookTargetColumn) {
            if (changeXPosition > 0) {
                for (int i = rookCurrentRow + 1; i < rookTargetRow; i++) {
                    if (tiles[i][rookCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
                        valid = true;
                    } else {
                        return false;
                    }
                }
                valid = true;
            }

            if (changeXPosition < 0) {
                for (int i = rookCurrentRow - 1; i > rookTargetRow; i--) {
                    if (tiles[i][rookCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
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
        int rookCurrentRow, rookCurrentColumn;
        int rookTargetRow = destination.getRow();
        int rookTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        rookCurrentRow = source.getRow();
        rookCurrentColumn = source.getColumn();

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (!canMoveToEat(destination)) {
            return false;
        }

        if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null && tiles[rookCurrentRow][rookCurrentColumn].getPiece() != null) {
            if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor())
                return true;
        }
        return false;

    }

    public boolean canMoveToEat(Coordinate destination) {
        int rookCurrentRow, rookCurrentColumn;
        int rookTargetRow = destination.getRow();
        int rookTargetColumn = destination.getColumn();

        Coordinate source = getCoordinate();
        rookCurrentRow = source.getRow();
        rookCurrentColumn = source.getColumn();

        int changeXPosition = rookTargetRow - rookCurrentRow;
        int changeYPosition = rookTargetColumn - rookCurrentColumn;

        Tile[][] tiles = ChessBoardController.tileMatrix;

        if (rookCurrentRow == rookTargetRow) {
            if (changeYPosition > 0) {
                for (int i = rookCurrentColumn + 1; i < rookTargetColumn; i++) {
                    if (tiles[rookCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }

            if (changeYPosition < 0) {
                for (int i = rookCurrentColumn - 1; i > rookTargetColumn; i--) {
                    if (tiles[rookCurrentRow][i].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }

        if (rookCurrentColumn == rookTargetColumn) {
            if (changeXPosition > 0) {
                for (int i = rookCurrentRow + 1; i < rookTargetRow; i++) {
                    if (tiles[i][rookCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }

            if (changeXPosition < 0) {
                for (int i = rookCurrentRow - 1; i > rookTargetRow; i--) {
                    if (tiles[i][rookCurrentColumn].getPiece() != null) {
                        return false;
                    }
                }

                if (tiles[rookTargetRow][rookTargetColumn].getPiece() != null) {
                    if (tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() != tiles[rookTargetRow][rookTargetColumn].getPiece().getColor()) {
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