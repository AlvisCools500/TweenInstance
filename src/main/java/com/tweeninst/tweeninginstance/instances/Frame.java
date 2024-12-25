package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.IEnums.IEnum;
import com.tweeninst.tweeninginstance.instServ;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Frame extends Instance {


    @Override
    public void update() {
        super.update();
    }

    @Override
    public void setDefault() {
        instServ.setDefaults(properties);
        this.type = IEnum.IsA.Frame;
        this.objectAbstract = new Rectangle(100, 100, 200, 200);
        this.objectAbstract.setFill(Color.BLACK);
        System.out.println("Set defaulting");
        super.setDefault();

    }
}
