package com.tweeninst.tweeninginstance;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    public static void main(String[] args) {launch();}

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    private void OnStart() {
        Frame frame = new Frame();

        TweenChains myChain = new TweenChains();

        myChain.addKey(frame, 0.2, EasingService.EasingStyle.EXPONENTIAL, EasingService.EasingDirection.OUT);
        myChain.addKey(frame, 0.25, EasingService.EasingStyle.EXPONENTIAL, EasingService.EasingDirection.IN);
        myChain.addKey(frame, 0.25, EasingService.EasingStyle.CUBIC, EasingService.EasingDirection.OUT);

        myChain.updateProperty.setSize(new UDim2(0.2, 0, 0.2 ,0));
        myChain.pushKey(0, true);
        myChain.updateProperty.setSize(new UDim2(0.4, 0, 0.4 ,0));
        myChain.pushKey(1, true);
        myChain.updateProperty.setSize(new UDim2(0.6, 0, 0.6 ,0));
        myChain.pushKey(2, true);

        task.Wait(1);

        myChain.play();

    }

}