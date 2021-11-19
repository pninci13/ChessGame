package com.visual.chess.assets;

import com.visual.chess.controllers.ChessBoardController;
import com.visual.chess.assets.Tile;

public class Pawn extends Piece {
    public boolean wasMoved = false;

    public Pawn(int color) {
        super(color);
    }

    @Override
    public boolean canMove(int changeXPosition, int changeYPosition) {
        int pawnCurrentRow = ChessBoardController.currentRow;
        int pawnCurrentColumn = ChessBoardController.currentColumn;
        int pawnTargetRow = ChessBoardController.targetRow;
        int pawnTargetColumn = ChessBoardController.targetColumn;
        Tile[][] tiles = ChessBoardController.tileMatrix;

        int i = 1;

        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color == Piece.WHITE) {
            i = -1;
        }

        boolean canEat = (Math.abs(changeXPosition) == 1 && ((changeYPosition == 1) || (changeYPosition == -1 )));

        //consertar canEat -> pode ir para as duas diagonais
        boolean backwardMovement = (pawnCurrentRow + (-i) == pawnTargetRow);


        if (!wasMoved) {
            if(!backwardMovement) {
                if (canEat) {
                    if((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)){
                        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color != tiles[pawnTargetRow][pawnTargetColumn].getPiece().color) {
                            if (tiles[pawnCurrentRow + i][pawnCurrentColumn + i].getPiece() != null) {
                                wasMoved = true;
                                return true;
                            }else if (tiles[pawnCurrentRow + i][pawnCurrentColumn - i].getPiece() != null){
                                wasMoved = true;
                                return true;
                            }
                        }
                    }
                }

                if ((Math.abs(changeXPosition) <= 2 && changeYPosition == 0) && (tiles[pawnCurrentRow + 2 * i][pawnCurrentColumn].getPiece() == null)) {
                    wasMoved = true;
                    return true;

                } else if ((Math.abs(changeXPosition) <= 1 && changeYPosition == 0) && tiles[pawnCurrentRow + i][pawnCurrentColumn].getPiece() == null) {
                    wasMoved = true;
                    return true;

                }

            }
            return false;

        }else {
            if(!backwardMovement) {
                if (canEat) {
                    if((tiles[pawnCurrentRow][pawnCurrentColumn].getPiece() != null) && (tiles[pawnTargetRow][pawnTargetColumn].getPiece() != null)){
                        if (tiles[pawnCurrentRow][pawnCurrentColumn].getPiece().color != tiles[pawnTargetRow][pawnTargetColumn].getPiece().color) {
                            if (tiles[pawnCurrentRow + i][pawnCurrentColumn + i].getPiece() != null) {
                                return true;
                            }if (tiles[pawnCurrentRow + i][pawnCurrentColumn - i].getPiece() != null){
                                return true;
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

}