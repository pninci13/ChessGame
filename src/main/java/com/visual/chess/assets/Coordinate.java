package com.visual.chess.assets;

import java.util.Objects;

public class Coordinate {
    private int rowCoordinate;
    private int columnCoordinate;

    public Coordinate(int row, int col) {
        rowCoordinate = row;
        columnCoordinate = col;
    }

    public int getRow() {
        return rowCoordinate;
    }

    public void setRow(int rowCoordinate) {
        this.rowCoordinate = rowCoordinate;
    }

    public int getColumn() {
        return columnCoordinate;
    }

    public void setColumn(int columnCoordinate) {
        this.columnCoordinate = columnCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;

        return rowCoordinate == that.rowCoordinate && columnCoordinate == that.columnCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowCoordinate, columnCoordinate);
    }
}