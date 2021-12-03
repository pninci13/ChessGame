package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;

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
        int i = 1;

        Piece piece = tiles[pawnCurrentRow][pawnCurrentColumn].getPiece();

        if (piece == null || source.equals(destination))
            return false;

//        boolean backwardMovement = (changeXPosition  < 0);

        if (piece.getColor() == Piece.WHITE) {
//            backwardMovement = (changeXPosition > 0);
            i = -1;
        }

        boolean backwardMovement = (pawnCurrentRow + (-i) == pawnTargetRow) || (pawnCurrentRow + (-i * 2) == pawnTargetRow);

        boolean valid = false;

        if (!wasMoved()) {
            if (!backwardMovement) {
                if (Math.abs(changeXPosition) == 1 && ((changeYPosition == 1) || (changeYPosition == -1))) {
                    if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)) {
                        if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().getColor() != tiles[pawnTargetRow][pawnTargetColumn].getPiece().getColor())) {
//                            setWasMoved(true);
                            valid = true;
                        }
                    }
                }

                if ((Math.abs(changeXPosition) <= 2 && changeYPosition == 0) && (tiles[pawnCurrentRow + 2 * i][pawnCurrentColumn].getPiece() == null)) {
                    if (tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null) {
//                        setWasMoved(true);
                        valid = true;
                    }
                } else if (tiles[pawnCurrentRow + 2 * i][pawnCurrentColumn].getPiece() != null) {
                    if ((Math.abs(changeXPosition) <= 1 && changeYPosition == 0) && (tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null)) {
//                        setWasMoved(true);
                        valid = true;
                    }

                }
            }

        } else {
            if (!backwardMovement) {
                if (Math.abs(changeXPosition) == 1 && ((changeYPosition == 1) || (changeYPosition == -1))) {
                    if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)) {
                        if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().getColor() != tiles[pawnTargetRow][pawnTargetColumn].getPiece().getColor()))
                            valid = true;
                    }
                }

                if ((Math.abs(changeXPosition) <= 1 && changeYPosition == 0) && tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null)
                    valid = true;
            }
        }

        if (valid) {
            if (ChessBoardController.isCheck(BLACK)) {
                boolean cb = canBlockCheck(destination);
//                if (cb) {
//                    System.out.println("PODE BLOQUEAR VADIA -> (" + destination.getRow() + ", " + destination.getColumn() + ")");
//                } else {
//                    System.out.println("PODE BLOQUEAR NAO -> (" + destination.getRow() + ", " + destination.getColumn() + ")");
//                }
                return cb;
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean canMoveToBlock(Coordinate destination) {
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

        if (source.equals(destination)) {
            return false;
        }

        boolean backwardMovement = (pawnCurrentRow + (-i) == pawnTargetRow);

        if (!wasMoved()) {
            if (!backwardMovement) {
                if (Math.abs(changeXPosition) == 1 && ((changeYPosition == 1) || (changeYPosition == -1))) {
                    if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)) {
                        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color != tiles[pawnTargetRow][pawnTargetColumn].getPiece().color) {
                            if (!(pawnCurrentRow + i == 8 || pawnCurrentRow + i == -1 || pawnCurrentColumn + i == 8 || pawnCurrentColumn + i == -1)) {
                                if (tiles[pawnCurrentRow + i][pawnCurrentColumn + i].getPiece() != null) {
                                    return true;
                                }
                            } else if (!(pawnCurrentRow + i == 8 || pawnCurrentRow + i == -1 || pawnCurrentColumn - i == 8 || pawnCurrentColumn - i == -1)) {
                                if (tiles[pawnCurrentRow + i][pawnCurrentColumn - i].getPiece() != null) {
                                    return true;
                                }
                            }
                        }
                    }
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
            }
            return false;

        } else {
            if (!backwardMovement) {
                if (Math.abs(changeXPosition) == 1 && ((changeYPosition == 1) || (changeYPosition == -1))) {
                    if ((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)) {
                        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color != tiles[pawnTargetRow][pawnTargetColumn].getPiece().color) {
                            if (!(pawnCurrentRow + i == 8 || pawnCurrentRow + i == -1 || pawnCurrentColumn + i == 8 || pawnCurrentColumn + i == -1)) {
                                if (tiles[pawnCurrentRow + i][pawnCurrentColumn + i].getPiece() != null) {
                                    return true;
                                }
                            } else if (!(pawnCurrentRow + i == 8 || pawnCurrentRow + i == -1 || pawnCurrentColumn - i == 8 || pawnCurrentColumn - i == -1)) {
                                if (tiles[pawnCurrentRow + i][pawnCurrentColumn - i].getPiece() != null) {
                                    return true;
                                }
                            }
                        }
                    }
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

        Piece piece = tiles[pawnCurrentRow][pawnCurrentColumn].getPiece();
        if (piece != null && piece.getColor() == Piece.WHITE) {
            i = -1;
        }

        if (changeXPosition != i)
            return false;

        if (source.equals(destination)) {
            return false;
        }

        if (Math.abs(changeYPosition) == 1) {
            if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null && tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null) {
                return tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color != tiles[pawnTargetRow][pawnTargetColumn].getPiece().color;
            }
        }
        return false;
    }
}