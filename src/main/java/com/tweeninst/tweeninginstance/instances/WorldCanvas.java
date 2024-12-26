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
    HashMap<Integer, ArrayList<Instance>> ListInstance = new HashMap<>();

    private void clearChildren() {
        Platform.runLater(() -> {
            this.getChildren().clear();

        });
    }

    public void updateRoot() {
        System.out.println("Updating");

        clearChildren();
        ListInstance.clear();


        ArrayList<Integer> sortedZ = new ArrayList<>();

        for (var a : ListAdded.entrySet()) {
            Instance v = ListAdded.get(a.getKey());

            int ZIndex = v.properties.getZIndex();

            if (ListInstance.get(ZIndex) == null) {
                ListInstance.put(ZIndex, new ArrayList<>());
                sortedZ.add(ZIndex);
            }

            ListInstance.get(ZIndex).add(v);
        }

        sortedZ.sort(Integer::compareTo);

        for (int ZIndex : sortedZ) {
            for (Instance inst : ListInstance.get(ZIndex)) {
                if (inst.objectAbstract != null) {
                    System.out.println("adding " + inst.type);
                    Platform.runLater(() -> {
                        this.getChildren().add(inst.objectAbstract);
                    });
                }else {
                    System.out.println("This instance has no Abstract");
                }
            }
        }

        System.out.println("Root Updated! " + ListAdded.size() + " Total");
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

            for (var a : ListInstance.entrySet()) {
                boolean fond = false;
                ArrayList<Instance> arrayInst = ListInstance.get(a.getKey());

                for (int index=arrayInst.size() - 1; index>=0; index++) {

                    Instance v = arrayInst.get(index);

                    if (v.uuid == inst.uuid) {
                        arrayInst.remove(index);
                        fond = true;
                        break;
                    }
                }

                if (arrayInst.size() < 1) {
                    ListInstance.remove(a.getKey());
                }

                if (fond) {
                    break;
                }
            }

            updateRoot();
        }
    }
}

