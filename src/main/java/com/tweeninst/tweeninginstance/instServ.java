package com.tweeninst.tweeninginstance;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import com.tweeninst.tweeninginstance.IEnums.*;
import com.tweeninst.tweeninginstance.instances.*;
import com.tweeninst.tweeninginstance.vectors.*;

import java.util.*;


public class instServ {
    public static void setDefaults(HashProperty prop) {
        prop.setPosition(new UDim2(0.5,0,0.5,0));
        prop.setColor(Color.rgb(0,0,0));
        prop.setRotation(0);
        prop.setSize(new UDim2(0.5, 0, 0.5, 0));
        prop.setTransparency(1);
        prop.setAnchorPoint(new VectorDouble2D(0.5,0.5));
        prop.setZIndex(0);

    }
}
