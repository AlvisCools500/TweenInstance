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

        ArrayList<Tween> arrTween = new ArrayList<>();

        arrTween.add(new Tween(frame, 0.2, EasingService.EasingStyle.EXPONENTIAL, EasingService.EasingDirection.OUT));
        arrTween.get(0).propertyTarg.setSize(new UDim2(0.3, 0, 0.3, 0));

        arrTween.add(new Tween(frame, 0.4, EasingService.EasingStyle.EXPONENTIAL, EasingService.EasingDirection.IN));
        arrTween.get(1).propertyTarg.setSize(new UDim2(0.5, 0, 0.5, 0));

        arrTween.add(new Tween(frame, 1, EasingService.EasingStyle.ELASTIC, EasingService.EasingDirection.OUT));
        arrTween.get(2).propertyTarg.setSize(new UDim2(0.7, 0, 0.7, 0));

        task.Wait(1);

        for (int i=0; i<=arrTween.size()-1; i++) {
            arrTween.get(i).play();
            arrTween.get(i).waitEnd();
        }

        for (int i = arrTween.size() - 1; i>=0; i--) {
            arrTween.get(i).Destroy(false);
            arrTween.set(i, null);
            arrTween.remove(i);
        }

        arrTween.clear();


    }

}