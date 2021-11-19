package com.visual.chess.assets;

import com.visual.chess.assets.Piece;

public class Tile {
    private Piece piece = null;

    public boolean isTileOccupied(){
        if(getPiece() == null){
            return false;
        }else{
            return true;
        }
    }

    public Piece getPieceFromTile(){
        if(isTileOccupied()){
            return piece;
        }else{
            return null;
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}