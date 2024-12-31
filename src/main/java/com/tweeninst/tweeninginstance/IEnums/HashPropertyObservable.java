package com.tweeninst.tweeninginstance.IEnums;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

public class HashPropertyObservable extends HashProperty implements ObservableValue<NaturalProperty> {
    List<ChangeListener<NaturalProperty>> ListListeners = new ArrayList<>();

    public void Destroy() {
        ListListeners.clear();
        ListListeners = null;

        for (var a : this.entrySet()) {
            this.remove(a.getKey());
        }

        this.clear();
    }

    @Override
    public void addListener(ChangeListener<? super NaturalProperty> listener) {
        ListListeners.add((ChangeListener<NaturalProperty>) listener);
    }

    @Override
    public void removeListener(ChangeListener<? super NaturalProperty> listener) {
        if (ListListeners != null) {
            try {
                ListListeners.remove(listener);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public NaturalProperty getValue() {
        return null;
    }

    private void CallListeners(NaturalProperty prevProp, NaturalProperty newProp) {
        for (ChangeListener<NaturalProperty> listener : ListListeners) {
            listener.changed(this, prevProp, newProp);
        }
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }

    @Override
    public Object put(IEnum.Properties key, Object value) {
        NaturalProperty previousProp = this.naturalClone();

        Object res = super.put(key, value);

        NaturalProperty newProp = this.naturalClone();

        CallListeners(previousProp, newProp);

        return res;

    }
}
