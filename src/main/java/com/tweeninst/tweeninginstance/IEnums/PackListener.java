package com.tweeninst.tweeninginstance.IEnums;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PackListener {
    HashMap<Object, ArrayList<ChangeListener<?>>> ListenerLists = new HashMap<>();

    public <T> void addListener(ObservableValue<T> property, ChangeListener<? super T> listener) {
        if (ListenerLists.get(property) == null) {
            ListenerLists.put(property, new ArrayList<>());
        }

        property.addListener(listener);
        ListenerLists.get(property).add(listener);
    }

    public <T> void addListenerProp(HashProperty prop, ChangeListener<? super T> listener) {
        if (ListenerLists.get(prop) == null) {
            ListenerLists.put(prop, new ArrayList<>());
        }

        prop.addListener((ChangeListener<Object>) listener);
        ListenerLists.get(prop).add(listener);
    }

    public void disconnectAll() {
        for (var a : ListenerLists.entrySet()) {
            Object property = a.getKey();

            for (ChangeListener<?> listener : ListenerLists.get(property)) {
                if (property instanceof ObservableValue) {
                    ((ObservableValue<Object>) property).removeListener((ChangeListener<Object>) listener);
                }else if (property instanceof HashProperty) {
                    ((HashProperty) property).removeListener((ChangeListener<Object>) listener);
                }
            }

        }

        ListenerLists.clear();
    }
}

