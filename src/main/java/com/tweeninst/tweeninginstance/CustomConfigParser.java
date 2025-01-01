package com.tweeninst.tweeninginstance;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import com.tweeninst.tweeninginstance.IEnums.*;
import com.tweeninst.tweeninginstance.instances.Frame;
import com.tweeninst.tweeninginstance.vectors.*;
import com.tweeninst.tweeninginstance.instances.*;

import javax.sound.sampled.Line;

public class CustomConfigParser {

    public static List<String> readConfig(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().startsWith("Examples") && !line.trim().isEmpty()) {
                lines.add(line);
            }
        }
        reader.close();
        return lines;
    }


    public static void parseLine(String line, HashMap<String, Object> vars) {


        if (line.contains(":")) {
            String command = line.substring(0, line.indexOf(' ')).trim();
            String arguments = line.substring(line.indexOf(' ')).trim();

           // System.out.println(command + " " + arguments);

            if (command.equals("inst")) { // Creates new Instance

                String nameVar = arguments.substring(0, arguments.indexOf(":")).trim();
                String typeInst = arguments.substring(arguments.indexOf(":") + 1).trim();
                Instance myInst = null;

                if (typeInst.equals("Frame")) {
                    myInst = new Frame();
                }else {
                    System.out.println("WRONG TYPE?");
                    System.out.println(typeInst);
                }

                if (myInst != null) {
                    if (vars.get(nameVar) != null) {
                        System.out.println(nameVar + " is duplicated variable!");
                        System.out.println("Line Err: " + line);
                    }else {
                        vars.put(nameVar, myInst);
                    }
                }


            } else if (command.equals("t") || command.equals("tc")) {
                String nameVar = arguments.substring(0,arguments.indexOf(":")).trim();
                String ParamInput = arguments.substring(arguments.indexOf(":") + 1).trim();
                String[] paramArray = ParamInput.split("/");

                if (command.equals("t")) {
                    String targetVar = paramArray[0];
                    double dur = (double) mainMod.convertToNumber(paramArray[1]);
                    EasingService.EasingStyle easSty = EasingService.EasingStyle.valueOf(paramArray[2]);
                    EasingService.EasingDirection easDir = EasingService.EasingDirection.valueOf(paramArray[3]);

                    if (vars.get(targetVar) != null) {
                        Tween myTween = new Tween((Instance) vars.get(targetVar), dur, easSty, easDir);
                        vars.put(nameVar, myTween);
                    }else {
                        System.out.println(targetVar + " does not exist!");
                        System.out.println("Line Err: " + line);
                    }


                }else if (command.equals("tc")) {

                }

            } else if (command.equals("play")) {
                String typeTween = arguments.substring(0, arguments.indexOf(":")).trim();
                String targetVar = arguments.substring(arguments.indexOf(":") + 1).trim();

                if (vars.get(targetVar) != null) {
                    if (typeTween.equals("t")) {
                        if (vars.get(targetVar).getClass().getSimpleName().equals("Tween")) {
                            Tween myTween = (Tween) vars.get(targetVar);

                            myTween.play();
                        }
                    }
                }


            } else if (command.equals("wait")) {
                String typeWait = arguments.substring(0, arguments.indexOf(":")).trim();
                String targetVar = arguments.substring(arguments.indexOf(":") + 1).trim();

                if (typeWait.equals("n")) {
                    double myNum = (double) mainMod.convertToNumber(targetVar);
                    task.Wait(myNum);
                    System.out.println("GO");
                }else if (typeWait.equals("t")) {
                    Tween myTween = (Tween) vars.get(targetVar);

                    myTween.waitEnd();
                    System.out.println("Yeehaw");
                }
            } else if (command.equals("propset")) {
                String nameVar = arguments.substring(0, arguments.indexOf(":")).trim();
                String ParamInput = arguments.substring(arguments.indexOf(":") + 1).trim();
                String[] paramArray = ParamInput.split("/");

                if (vars.get(nameVar) != null) {
                    if (vars.get(nameVar).getClass().getSimpleName().equals("Tween")) {
                        Tween myTween = (Tween) vars.get(nameVar);

                        String propIndex = paramArray[0];
                        String strValue = paramArray[1];

                        Object resVal = null;

                        try {
                            IEnum.Properties.valueOf(propIndex);
                            resVal = mainMod.stringToValue(strValue);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        if (resVal != null) {
                            myTween.propertyTarg.put(IEnum.Properties.valueOf(propIndex), resVal);
                        }else{
                            System.out.println("Line Err: " + line);
                        }
                    } else {
                        System.out.println("This variable isnt tween!");
                        System.out.println("Line Err: " + line);
                    }
                }else {
                    System.out.println("variable does not exist!");
                    System.out.println("Line Err: " + line);
                }

            } else { //If none of these then it's gonna think as variable
                String nameVar = command;
                String propIndex = arguments.substring(0, arguments.indexOf(":")).trim();
                String strValue = arguments.substring(arguments.indexOf(":") + 1).trim();

                Object resVal = null;

                if (vars.get(nameVar) != null) {
                    Instance inst = (Instance) vars.get(nameVar);

                    try {
                        IEnum.Properties.valueOf(propIndex);
                        resVal = mainMod.stringToValue(strValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    if (resVal != null) {
                        inst.properties.put(IEnum.Properties.valueOf(propIndex), resVal);
                    }else {
                        System.out.println("Line Err: " + line);
                    }
                }else {
                    System.out.println("this variable doesnt exist!");
                    System.out.println("Line Err: " + line);
                }
            }
        } else {
            System.out.println("Line: " + line);
        }
    }
}
