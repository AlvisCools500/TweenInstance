package com.tweeninst.tweeninginstance.instances;

public class NonInstance extends Instance {
    @Override
    public void setDefault() {
        this.parent = null;
    }
}
