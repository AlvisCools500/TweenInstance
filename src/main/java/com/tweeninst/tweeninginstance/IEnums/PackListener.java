package com.tweeninst.tweeninginstance.IEnums;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PackListener {
    HashMap<ObservableValue<?>, ArrayList<ChangeListener<?>>> ListenerLists = new HashMap<>();

    public <T> void addListener(ObservableValue<T> property, ChangeListener<? super T> listener) {
        if (ListenerLists.get(property) == null) {
            ListenerLists.put(property, new ArrayList<>());
        }

        property.addListener(listener);
        ListenerLists.get(property).add(listener);
    }

    public void disconnectAll() {
        for (Map.Entry<ObservableValue<?>, ArrayList<ChangeListener<?>>> entryArray: ListenerLists.entrySet()) {
            ObservableValue<?> property = entryArray.getKey();

            for (ChangeListener<?> listener : ListenerLists.get(property)) {
                if (property instanceof ObservableValue) {
                    ((ObservableValue<Object>) property).removeListener((ChangeListener<Object>) listener);
                }
            }

        }

        ListenerLists.clear();
    }
}

