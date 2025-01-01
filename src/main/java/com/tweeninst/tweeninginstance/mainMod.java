package com.tweeninst.tweeninginstance;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tweeninst.tweeninginstance.vectors.*;
import javafx.scene.paint.Color;

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

    public static Number convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e2) {
                System.out.println("Invalid number format: " + input);
                return null;
            }
        }
    }

    public static Object stringToValue(String str) {

        if (str.contains("UDim2") || str.contains("UDim") || str.contains("VectorInt2D") || str.contains("VectorDouble2D") || str.contains("Color")) {// UDim2
            String ParamSTR = str.substring(str.indexOf("(") + 1, str.indexOf(")")).trim();
            String[] splittedSTR = ParamSTR.split(",");

            if (str.contains("UDim2")) {
                return new UDim2(
                        (double) convertToNumber(splittedSTR[0]),
                        (double) convertToNumber(splittedSTR[1]),
                        (double) convertToNumber(splittedSTR[2]),
                        (double) convertToNumber(splittedSTR[3])
                );
            } else if (str.contains("UDim")) {
                return new UDim(
                        (double) convertToNumber(splittedSTR[0]),
                        (double) convertToNumber(splittedSTR[1])
                );
            } else if (str.contains("VectorInt2D")) {
                return new VectorDouble2D(
                        (double) convertToNumber(splittedSTR[0]),
                        (double) convertToNumber(splittedSTR[1])
                );
            } else if (str.contains("VectorDouble2D")) {
                return new VectorInt2D(
                        (int) convertToNumber(splittedSTR[0]),
                        (int) convertToNumber(splittedSTR[1])
                );
            } else if (str.contains("Color")) {
                return Color.rgb(
                        (int) convertToNumber(splittedSTR[0]),
                        (int) convertToNumber(splittedSTR[1]),
                        (int) convertToNumber(splittedSTR[2])
                );
            }
        }

        return null;
    }



}
