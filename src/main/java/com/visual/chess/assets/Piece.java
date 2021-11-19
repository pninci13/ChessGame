package com.visual.chess.assets;

import com.visual.chess.controllers.ChessController;

public abstract class Piece {
    int color;
    public static final int BLACK = 1;
    public static final int WHITE = 0;

    public Piece(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public abstract boolean canMove(int changeXPosition, int changeYPosition);

}