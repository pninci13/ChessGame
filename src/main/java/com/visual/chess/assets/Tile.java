package com.visual.chess.assets;

import com.visual.chess.assets.Piece;

public class Tile {
    private Piece piece = null;

    public boolean isTileOccupied() {
        return getPiece() != null;
    }

    public Piece getPieceFromTile() {
        if (isTileOccupied()) {
            return piece;
        } else {
            return null;
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public static Tile[][] cloneFrom(Tile[][] board) {
        Tile[][] newBoard = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = new Tile();
                Piece piece = board[i][j].getPiece();
                if (piece != null)
                    tile.setPiece(piece.clonePiece());
                newBoard[i][j] = tile;
            }
        }
        return newBoard;
    }
}