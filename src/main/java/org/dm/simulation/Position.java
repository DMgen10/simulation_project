package org.dm.simulation;

public record Position(int x, int y) {

    public Position{
        if (x < 0 || y < 0){
            throw new IllegalArgumentException("x or y cannot be less than zero");
        }
    }
}
