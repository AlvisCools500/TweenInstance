package com.tweeninst.tweeninginstance;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.tweeninst.tweeninginstance.IEnums.*;
import com.tweeninst.tweeninginstance.instances.*;
import com.tweeninst.tweeninginstance.vectors.*;


public class mainApp extends Application {

    WorldCanvas worldCanvas;
    public static World world;


    public static Canvas canvas = new Canvas(mainMod.Resolution.x, mainMod.Resolution.y);
    GraphicsContext g = canvas.getGraphicsContext2D();
    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        worldCanvas = new WorldCanvas();
        world = new World(worldCanvas);

        canvas.widthProperty().bind(worldCanvas.widthProperty());
        canvas.heightProperty().bind(worldCanvas.heightProperty());

        scene = new Scene(worldCanvas, mainMod.Resolution.x, mainMod.Resolution.y);

        stage.setTitle("Tweening Instance");
        stage.setScene(scene);
        stage.show();

        task.spawnTask(() -> OnStart());
    }

    private void Repainter(Instance inst) {
        HashMap<Integer, ArrayList<Instance>> listInst = new HashMap<>();
        ArrayList<Integer> sortZ = new ArrayList<>();

        HashChildren childrens = inst.childrens;

        // Sorting ZIndexes
        for (var a : childrens.entrySet()) {
            Instance v = a.getValue();

            int ZIndex = v.properties.getZIndex();

            if (listInst.get(ZIndex) == null) {
                listInst.put(ZIndex, new ArrayList<>());
                sortZ.add(ZIndex);
            }

            listInst.get(ZIndex).add(v);
        }

        // Draw

        for (var a : listInst.entrySet()) {
            
        }

    }

    public static void main(String[] args) {launch();}

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    private void OnStart() {
        Frame frame = new Frame();
        frame.properties.setSize(new UDim2(0.4,0,0.4,0));
    }

}