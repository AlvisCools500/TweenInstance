package com.tweeninst.tweeninginstance.vectors;

public class VectorDouble2D {
    public double x;
    public double y;

    // Creating Vector Int
    public VectorDouble2D(double X, double Y) {
        this.x = X;
        this.y = Y;
    }

    // Manual

    // This + (X,Y)
    public void plus(double X, double Y) {
        this.x += X;
        this.y += Y;
    }

    // This - (X,Y)
    public void substract(double X, double Y) {
        this.x -= X;
        this.y -= Y;
    }

    // This / (X,Y)
    public void divide(double X, double Y) {
        this.x /= X;
        this.y /= Y;
    }

    // This * (X,Y)
    public void multiply(double X, double Y) {
        this.x *= X;
        this.y *= Y;
    }

    // With Other Vectors

    // This + Other
    public void plus(VectorDouble2D other) {
        plus(other.x, other.y);
    }

    // This - (X,Y)
    public void substract(VectorDouble2D other) {
        substract(other.x, other.y);
    }

    // This / (X,Y)
    public void divide(VectorDouble2D other) {
        divide(other.x, other.y);
    }

    // This * (X,Y)
    public void multiply(VectorDouble2D other) {
        multiply(other.x, other.y);
    }

    @Override
    public String toString() {
        return "x" + this.x + " y" + this.y;
    }
}


