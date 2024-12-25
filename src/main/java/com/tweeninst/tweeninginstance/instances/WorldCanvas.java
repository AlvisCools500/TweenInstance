package com.tweeninst.tweeninginstance.instances;

import com.tweeninst.tweeninginstance.IEnums.IEnum;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
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

        ArrayList<Integer> sortedZ = new ArrayList<>();

        for (var v : ListInstance.entrySet()) {
            sortedZ.add(v.getKey());
        }

        sortedZ.sort(Integer::compareTo);

        for (int ZIndex : sortedZ) {
            for (Instance inst : ListInstance.get(ZIndex)) {
                if (inst.objectAbstract != null) {
                    this.getChildren().add(inst.objectAbstract);
                }else {
                    System.out.println("This instance has to Abstract");
                }
            }
        }
        System.out.println(this.getChildren());
        System.out.println("Root Updated! " + ListAdded.size() + " Total");
    }

    public void addInstance(Instance inst) {
        System.out.println("add trigger");
        if (ListAdded.get(inst.uuid) == null) {
            ListAdded.put(inst.uuid, inst);

            int ZIndex;

            if (inst.properties.get(IEnum.Properties.ZIndex) != null) {
                ZIndex = inst.properties.getZIndex();
            }else {
                ZIndex = 0;
            }

            if (ListInstance.get(ZIndex) == null) {
                ListInstance.put(ZIndex,new ArrayList<>());
            }

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

