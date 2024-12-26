package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.IEnums.*;
import com.tweeninst.tweeninginstance.vectors.*;
import com.tweeninst.tweeninginstance.*;

public class World extends Instance {
    WorldCanvas TargetCanvas;

    public World(WorldCanvas wrld) {
        TargetCanvas = wrld;
    }

    public VectorDouble2D getAbsoluteSize() {
        VectorDouble2D resVect = new VectorDouble2D(mainApp.canvas.getWidth(),mainApp.canvas.getWidth());

        return resVect;
    }

    @Override
    public void addChild(Instance targInst) {
        super.addChild(targInst);

        TargetCanvas.addInstance(targInst);
    }

    @Override
    public void removeChild(Instance targInst) {
        TargetCanvas.removeInstance(targInst);

        super.removeChild(targInst);
    }

    @Override
    public void setDefault() {
        this.parent = null;
        this.type = IEnum.IsA.World;

        properties.setPosition(new UDim2(0.5, 0, 0.5, 0));
        properties.setAnchorPoint(new VectorDouble2D(0.5,0.5));
    }

}

