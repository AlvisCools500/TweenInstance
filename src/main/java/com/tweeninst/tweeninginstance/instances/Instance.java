package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.mainApp;
import com.tweeninst.tweeninginstance.IEnums.*;
import com.tweeninst.tweeninginstance.vectors.*;
import com.tweeninst.tweeninginstance.*;

import javafx.scene.shape.Shape;

import java.util.UUID;

public class Instance {
    public HashProperty properties = new HashProperty();
    public Instance parent;
    Instance previousParent;
    public HashChildren childrens = new HashChildren();
    public UUID uuid = UUID.randomUUID();
    public IEnum.IsA type = IEnum.IsA.Non;
    public String name = "Instance";
    public PackListener listeners = new PackListener();
    public Shape objectAbstract;

    public Instance() {
        this.setDefault();
    }

    public void update() {}

    public void setDefault() {
        this.setParent(mainApp.world);
    }

    public void setParent(Instance targetInst) {
        if (this.parent != null) {
            this.previousParent = this.parent;
            this.parent.removeChild(this);
        }

        targetInst.addChild(this);
    }

    public void addChild(Instance targInst) {
        System.out.println("addchild " + targInst.type + " to " + type + " ClassTarget: " + targInst.getClass().getSimpleName());
        if (targInst.parent == null) {
            targInst.parent = this;
            this.childrens.put(targInst.uuid, targInst);
        }
    }

    public void removeChild(Instance targInst) {
        if (this.childrens.get(targInst.uuid) != null && targInst.parent == this) {
            this.childrens.remove(targInst.uuid);

            targInst.parent = null;

            listeners.disconnectAll();
        }
    }

    public Instance FindFirstClass(IEnum.IsA instType) {
        for (var a : this.childrens.entrySet()) {
            if (a.getValue().type == instType) {
                return this.childrens.get(a.getKey());
            }
        }

        return null;
    }

    public Instance FindFirstName(String name) {
        for (var a : this.childrens.entrySet()) {
            if (a.getValue().name.equals(name)) {
                return this.childrens.get(a.getKey());
            }
        }

        return null;
    }

    public VectorDouble2D getAbsolutePos() {
     return new VectorDouble2D(0,0);
    }

    public VectorDouble2D getAbsoluteSize() {
        return new VectorDouble2D(100,100);
    }
}