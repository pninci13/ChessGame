package com.visual.chess.assets;
import com.visual.chess.controllers.ChessBoardController;
import com.visual.chess.assets.Tile;

public class Rook extends Piece {
    public Rook(int color) {
        super(color);
    }

    @Override
    public boolean canMove(int changeXPosition, int changeYPosition){
        int rookCurrentRow = ChessBoardController.currentRow;
        int rookCurrentColumn = ChessBoardController.currentColumn;
        int rookTargetRow = ChessBoardController.targetRow;
        int rookTargetColumn = ChessBoardController.targetColumn;
        Tile[][] tiles = ChessBoardController.tileMatrix;

        System.out.println("deltax = " + Math.abs(changeXPosition));
        System.out.println("deltay = " + Math.abs(changeYPosition));
        System.out.println("linha = " + rookTargetRow);
        System.out.println("coluna = " + rookTargetColumn);

        if(tiles[rookCurrentRow][rookCurrentColumn].getPiece().getColor() == 1) {
            for (int i = 0; i < 7; i++) {
                if ((tiles[i][rookCurrentRow].getPiece() != null) && (Math.abs(changeXPosition) >= i)) {
                    return false;
                }
            }

            for (int j = 0; j < 7; j++) {
                if ((tiles[rookCurrentRow][j].getPiece() != null) && (Math.abs(changeXPosition) >= j)) {
                    return false;
                }
            }
        }

        if((rookCurrentRow == rookTargetRow) || (rookCurrentColumn == rookTargetColumn)){
//            for (int i = 0; i < 8; i++) {
//                if(tiles[rookCurrentRow][i].getPiece() != null){
//                    return false;
//                }
//            }


            System.out.println("Se mexe torre");
            return true;
        }else{
            System.out.println("torre n mexeu");
            return false;
        }

    }

//    @Override
//    public void showPath() {
//
//    }

}