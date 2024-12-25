package com.tweeninst.tweeninginstance;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tweeninst.tweeninginstance.vectors.*;

public class mainMod {
    public static VectorDouble2D Resolution = new VectorDouble2D(800, 800);

    public static long GetLongClock() {
        return System.nanoTime();
    }

    public static double GetDoubleClock() {
        long TheClock = GetLongClock();

        double ResClock = (TheClock / 1e9);

        return ResClock;
    }

    public static float GetFloatClock() {
        long TheClock = GetLongClock();
        float ResClock = (float) (TheClock / 1e9);



        return ResClock;
    }



}
