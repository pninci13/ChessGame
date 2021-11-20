package com.visual.chess.assets;

import com.visual.chess.controllers.ChessController;

import java.util.ArrayList;

public abstract class Piece {
    int color;
    public static final int BLACK = 1;
    public static final int WHITE = 0;
    private Coordinate coordinate;
    private boolean wasMoved = false;

    private ArrayList<Coordinate> nextMoves = new ArrayList<>();

    public Piece(int color) {
        this.color = color;
    }

    public boolean wasMoved() {
        return wasMoved;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int row, int col) {
        this.coordinate = new Coordinate(row, col);
    }

    public abstract boolean canMove(Coordinate destination);

    public abstract boolean canEat(Coordinate destination);

    public ArrayList<Coordinate> getNextMoves() {
        return nextMoves;
    }

    public void setNextMoves(ArrayList<Coordinate> nextMoves) {
        this.nextMoves = nextMoves;
    }


}