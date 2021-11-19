package com.visual.chess.assets;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
    }

    @Override
    public boolean canMove(int changeXPosition, int changeYPosition){
//        if(Math.abs(changeXPosition) >= 2 && changeYPosition == 0){
        return true;
//        }else{
//            return false;
//        }
    }

//    @Override
//    public void showPath() {
//
//    }

}