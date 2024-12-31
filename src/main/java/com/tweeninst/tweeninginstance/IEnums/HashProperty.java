package com.tweeninst.tweeninginstance.IEnums;

import com.tweeninst.tweeninginstance.IEnums.*;
import com.tweeninst.tweeninginstance.vectors.*;
import com.tweeninst.tweeninginstance.instances.*;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashProperty extends NaturalProperty  {

    public NaturalProperty naturalClone() {
        return (NaturalProperty) this.clone();
    }

    // Position
    public void setPosition(UDim2 val) {
        this.put(IEnum.Properties.Position, val);
    }

    public UDim2 getPosition() {
        return (UDim2) this.get(IEnum.Properties.Position);
    }

    // Size
    public void setSize(UDim2 val) {
        this.put(IEnum.Properties.Size, val);
    }

    public UDim2 getSize() {
        return (UDim2) this.get(IEnum.Properties.Size);
    }

    // Anchor Point
    public void setAnchorPoint(VectorDouble2D val) {
        this.put(IEnum.Properties.AnchorPoint, val);
    }

    public VectorDouble2D getAnchorPoint() {
        return (VectorDouble2D) this.get(IEnum.Properties.AnchorPoint);
    }

    // Color
    public void setColor(Color val) {
        this.put(IEnum.Properties.Color, val);
    }

    public Color getColor() {
        return (Color) this.get(IEnum.Properties.Color);
    }

    // Transparency
    public void setTransparency(double val) {
        this.put(IEnum.Properties.Transparency, val);
    }

    public double getTransparency() {
        return (double) this.get(IEnum.Properties.Transparency);
    }

    // ZIndex
    public void setZIndex(int val) {
        this.put(IEnum.Properties.ZIndex, (int) val);
    }

    public int getZIndex() {
        return (int) this.get(IEnum.Properties.ZIndex);
    }

    // Rotation
    public void setRotation(double val) {
        this.put(IEnum.Properties.Rotation, val);
    }

    public double getRotation() {
        return (double) this.get(IEnum.Properties.Rotation);
    }

    // SizeRatio
    public void setSizeRatio(double val) {
        this.put(IEnum.Properties.SizeRatio, val);
    }

    public double getSizeRatio() {
        return (double) this.get(IEnum.Properties.SizeRatio);
    }

    // SizeCorner
    public void setSizeCorner(double val) {
        this.put(IEnum.Properties.SizeCorner, val);
    }

    public double getSizeCorner() {
        return (double) this.get(IEnum.Properties.SizeCorner);
    }





}