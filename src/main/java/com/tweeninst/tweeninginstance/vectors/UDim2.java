package com.tweeninst.tweeninginstance.vectors;

import com.tweeninst.tweeninginstance.instances.WorldCanvas;
import com.tweeninst.tweeninginstance.*;

public class UDim2 {
    public UDim x;
    public UDim y;

    public UDim2(double ScaleX, double OffsetX, double ScaleY, double OffsetY) {
        x = new UDim(ScaleX, OffsetX);
        y = new UDim(ScaleY, OffsetY);
    }

    public UDim2(UDim UX, UDim UY) {
        x = UX;
        y = UY;
    }

    public VectorDouble2D GetAbsolute(VectorDouble2D MainRatio) {
        double Width;
        double Height;

        if (MainRatio != null) {
            Width = MainRatio.x;
            Height = MainRatio.y;
        }else {
            Width = mainApp.canvas.getWidth();
            Height = mainApp.canvas.getHeight();
        }


        VectorDouble2D ResVect = new VectorDouble2D(Width, Height);

        ResVect.x = (ResVect.x * this.x.scale) + this.x.offset;
        ResVect.y = (ResVect.y * this.y.scale) + this.y.offset;

        return ResVect;
    }

    public VectorDouble2D GetAbsoluteRatio(VectorDouble2D MainRatio, double ratio) {
        double Width = MainRatio.x;
        double Height = MainRatio.y;

        double newWidth = (Height * ratio);
        double newHeight = Height;

        if (newWidth > Width) {
            newWidth = Width;
            newHeight = (Width / ratio);
        }

        return new VectorDouble2D(newWidth, newHeight);
    }

    @Override
    public String toString() {
        String str = "xs" + x.scale + " xo" + x.offset + " ys" + y.scale + " yo" + y.offset;

        return str;
    }
}
