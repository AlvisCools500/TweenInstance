package com.tweeninst.tweeninginstance.IEnums;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public <T> void addListenerProp(HashPropertyObservable prop, ChangeListener<? super T> listener) {
        if (ListenerLists.get(prop) == null) {
            ListenerLists.put(prop, new ArrayList<>());
        }

        prop.addListener((ChangeListener<Object>) listener);
        ListenerLists.get(prop).add(listener);
    }

    public int getSize() {
        if (this.ListenerLists.size() > 0) {
            int total = 0;

            for (var a : ListenerLists.entrySet()) {
                for (var b : ListenerLists.get(a.getKey())) {
                    total += 1;
                }
            }

            return total;
        }else {
            return 0;
        }

    }

    public void disconnectAll() {
        System.out.println(ListenerLists.size());

        for (var a : ListenerLists.entrySet()) {
            Object property = a.getKey();

            if (ListenerLists.get(property) != null) {
                for (ChangeListener<?> listener : ListenerLists.get(property)) {
                    if (property instanceof ObservableValue) {
                        ((ObservableValue<Object>) property).removeListener((ChangeListener<Object>) listener);
                    }else if (property instanceof HashProperty) {
                        ((HashPropertyObservable) property).removeListener((ChangeListener<Object>) listener);
                    }
                }
            }else {
                System.out.println("why it's getting null value!");
            }

        }

        ListenerLists.clear();

        for (var a : ListenerLists.entrySet()) {
            ListenerLists.remove(a.getKey());
        }




    }
}

