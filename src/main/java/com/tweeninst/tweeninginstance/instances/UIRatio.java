package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.IEnums.IEnum;
import com.tweeninst.tweeninginstance.vectors.VectorDouble2D;
import javafx.beans.value.ObservableValue;

public class UIRatio extends Instance {
    @Override
    public void setDefault() {
        this.parent = null;
        this.type = IEnum.IsA.RatioConstraint;
    }

    public UIRatio(Double ratio) {

        this.properties.setSizeRatio(ratio);

        this.listeners.addListenerProp(this.properties, this::update);

        this.updateParent();
    }

    private void update(ObservableValue<?> obsVal, Object oldVal, Object newVal) {
        updateParent();
    }

    private void updateParent() {
        if (this.parent != null) {
            Frame myPar = (Frame) this.parent;

            myPar.update();
        }
    }

    @Override
    public void setParent(Instance targetInst) {
        super.setParent(targetInst);
        this.updateParent();
    }
}
