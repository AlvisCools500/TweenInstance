package com.tweeninst.tweeninginstance.vectors;

public class UDim {
    public double scale;
    public double offset;

    public UDim(double Scale, double Offset) {
        scale = Scale;
        offset = Offset;
    }

    @Override
    public String toString() {
        return "sc" + this.scale + " of" + this.offset;
    }
}

