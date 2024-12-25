package com.tweeninst.tweeninginstance.vectors;

public class VectorInt2D {
    int x;
    int y;

    // Creating Vector Int
    public VectorInt2D(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

    // Manual

    // This + (X,Y)
    public void plus(int X, int Y) {
        this.x += X;
        this.y += Y;
    }

    // This - (X,Y)
    public void substract(int X, int Y) {
        this.x -= X;
        this.y -= Y;
    }

    // This / (X,Y)
    public void divide(int X, int Y) {
        this.x /= X;
        this.y /= Y;
    }

    // This * (X,Y)
    public void multiply(int X, int Y) {
        this.x *= X;
        this.y *= Y;
    }

    // With Other Vectors

    // This + Other
    public void plus(VectorInt2D other) {
        plus(other.x, other.y);
    }

    // This - (X,Y)
    public void substract(VectorInt2D other) {
        substract(other.x, other.y);
    }

    // This / (X,Y)
    public void divide(VectorInt2D other) {
        divide(other.x, other.y);
    }

    // This * (X,Y)
    public void multiply(VectorInt2D other) {
        multiply(other.x, other.y);
    }

    @Override
    public String toString() {
        return "x" + this.x + " y" + this.y;
    }
}

