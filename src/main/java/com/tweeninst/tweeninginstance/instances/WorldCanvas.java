package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.IEnums.IEnum;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WorldCanvas extends Pane {
    private HashMap<UUID, Instance> ListAdded = new HashMap<>();


    private void clearChildren() {
        Platform.runLater(() -> {
            this.getChildren().clear();

        });
    }

    public void updateRoot() {
        System.out.println("Updating");

        clearChildren();

        NonInstance ultraInst = new NonInstance();

        for (var a : ListAdded.entrySet()) {
            Instance inst = ListAdded.get(a.getKey());

            ultraInst.childrens.put(inst.uuid,inst);
        }

        recursiveRoot(ultraInst);

        System.out.println("Root Updated! " + ListAdded.size() + " Total");
    }

    private void recursiveRoot(Instance mainparent) {
        HashMap<Integer, ArrayList<Instance>> ListInstance = new HashMap<>();
        ArrayList<Integer> sortedZ = new ArrayList<>();

        for (var a : mainparent.childrens.entrySet()) {
            Instance v = mainparent.childrens.get(a.getKey());

            if (v.properties.get(IEnum.Properties.ZIndex) != null) {
                int ZIndex = v.properties.getZIndex();

                if (ListInstance.get(ZIndex) == null) {
                    ListInstance.put(ZIndex, new ArrayList<>());
                    sortedZ.add(ZIndex);
                }

                ListInstance.get(ZIndex).add(v);
            }
        }

        sortedZ.sort(Integer::compareTo);

        for (int ZIndex : sortedZ) {
            for (Instance inst : ListInstance.get(ZIndex)) {
                if (inst.objectAbstract != null) {
                    System.out.println("adding " + inst.type);
                    Platform.runLater(() -> {
                        this.getChildren().add(inst.objectAbstract);
                    });

                    if (inst.childrens.size() > 0) {
                        System.out.println("instance have children detected!");
                        this.recursiveRoot(inst);
                    }
                }else {
                    System.out.println("This instance has no Abstract");
                }
            }
        }
    }

    public void addInstance(Instance inst) {
        System.out.println("add trigger");
        if (ListAdded.get(inst.uuid) == null) {
            ListAdded.put(inst.uuid, inst);


            updateRoot();
        }else {
            System.out.println("it is available");
        }
    }

    public void removeInstance(Instance inst) {
        System.out.println("remove trigger");

        if (ListAdded.get(inst.uuid) != null) {
            ListAdded.remove(inst.uuid);
            updateRoot();
        }
    }
}

