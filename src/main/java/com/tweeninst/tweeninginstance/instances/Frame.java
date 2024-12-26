package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.IEnums.HashProperty;
import com.tweeninst.tweeninginstance.IEnums.IEnum;
import com.tweeninst.tweeninginstance.instServ;
import com.tweeninst.tweeninginstance.vectors.VectorDouble2D;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.invoke.VolatileCallSite;

import com.tweeninst.tweeninginstance.*;

public class Frame extends Instance {


    @Override
    public void update() {
        super.update();
        if (this.parent != null) {
            VectorDouble2D parentSize;

            System.out.println("UPDATE");

            VectorDouble2D totalSize = this.getAbsoluteSize();
            VectorDouble2D totalPos = this.getAbsolutePos();

            Rectangle objAbst = (Rectangle) this.objectAbstract;

            var anchor = this.properties.getAnchorPoint();

            objAbst.setX(totalPos.x - (totalSize.x * anchor.x));
            objAbst.setY(totalPos.y - (totalSize.y * anchor.y));
            objAbst.setWidth(totalSize.x);
            objAbst.setHeight(totalSize.y);

        }else {
            System.out.println("Fail to update");
        }
    }

    public void observeUpdate(ObservableValue<?> obsVal, Object oldVal, Object newVal) {
        this.update();
    }

    public void resetBind() {
        this.listeners.disconnectAll();

        this.listeners.addListenerProp(this.properties, this::observeUpdate);
        this.listeners.addListener(mainApp.canvas.heightProperty(), this::observeUpdate);
        this.listeners.addListener(mainApp.canvas.widthProperty(), this::observeUpdate);
    }

    @Override
    public void setDefault() {
        instServ.setDefaults(properties);
        this.type = IEnum.IsA.Frame;
        this.objectAbstract = new Rectangle(100, 100, 200, 200);
        this.objectAbstract.setFill(Color.BLACK);
        System.out.println("Set defaulting");

        super.setDefault();

        this.resetBind();
        this.update();


    }

    @Override
    public VectorDouble2D getAbsolutePos() {

        if (this.parent != null) {
            VectorDouble2D parentSize;
            VectorDouble2D parentPos;

            if (this.parent.type == IEnum.IsA.World) {
                parentSize = new VectorDouble2D(mainApp.canvas.getWidth(), mainApp.canvas.getHeight());
                parentPos = new VectorDouble2D(0,0);
            }else {
                parentSize = this.parent.getAbsoluteSize();
                parentPos = this.parent.getAbsolutePos();
            }

            var myVect = this.properties.getPosition();

            VectorDouble2D resVect = new VectorDouble2D(0,0);

            resVect.x = parentPos.x + (parentSize.x * myVect.x.scale) + myVect.x.offset;
            resVect.y = parentPos.y + (parentSize.y * myVect.y.scale) + myVect.y.offset;

            return resVect;
        }

        return super.getAbsolutePos();

    }

    @Override
    public VectorDouble2D getAbsoluteSize() {

        if (this.parent != null) {
            VectorDouble2D parentSize;

            if (this.parent.type == IEnum.IsA.World) {
                parentSize = new VectorDouble2D(mainApp.canvas.getWidth(), mainApp.canvas.getHeight());
            }else {
                parentSize = this.parent.getAbsoluteSize();
            }

            var thisVect = this.properties.getSize();

            parentSize.x = (parentSize.x * thisVect.x.scale) + thisVect.x.offset;
            parentSize.y = (parentSize.y * thisVect.y.scale) + thisVect.x.offset;

            return parentSize;
        }

        return super.getAbsoluteSize();
    }
}
