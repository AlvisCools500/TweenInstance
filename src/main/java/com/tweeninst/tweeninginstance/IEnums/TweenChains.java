package com.tweeninst.tweeninginstance.IEnums;

import com.tweeninst.tweeninginstance.instances.Instance;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class TweenChains {
    class SubTween {
        /*
        * INDEX LIST
        * "Property" = NaturalProperty
        * "Time" = double
        * "Instance" = Instance
        * "Style" = EasingStyle
        * "Direction" = EasingDirection
        */

        HashProperty property = new HashProperty();
        double time = 0;
        Instance instance;
        EasingService.EasingStyle style = EasingService.EasingStyle.LINEAR;
        EasingService.EasingDirection direction = EasingService.EasingDirection.INOUT;
    }

    ArrayList<SubTween> keyframes = new ArrayList<>();
    public HashProperty updateProperty = new HashProperty();
    boolean isPlaying = false;
    AnimationTimer timer;
    CountDownLatch latchEvent = new CountDownLatch(1);

    public int addKey(Instance inst, double Time, EasingService.EasingStyle easSty, EasingService.EasingDirection easDir) {
        if (!this.isPlaying) {
            SubTween myHash = new SubTween();
            myHash.instance = inst;
            myHash.style = easSty;
            myHash.direction = easDir;
            myHash.time = Time;

            keyframes.add(myHash);

            System.out.println("index added!");

            return Math.max(keyframes.size() - 1, 0);
        }

        return -999;
    }

    public void pushKey(int Index, boolean reset) {
        if (!this.isPlaying) {
            SubTween mySub = null;

            try {
                mySub = keyframes.get(Index);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (mySub != null) {
                for (var a : updateProperty.entrySet()) {
                    mySub.property.put(a.getKey(), a.getValue());
                }

                System.out.println("index " + Index + " pushed!");

                if (reset) {
                    updateProperty.clear();
                }
            } else {
                System.out.println("out of index (TweenChains.java)");
            }
        }

    }

    public void play() {
        if (!this.isPlaying) {
            this.isPlaying = true;

            System.out.println("PLAY");

            for (int i = 0; i<=keyframes.size()-1; i++) {
                SubTween subTween = keyframes.get(i);

                Tween myTween = new Tween(subTween.instance, subTween.time, subTween.style, subTween.direction);
                myTween.propertyTarg = subTween.property;

                myTween.play();
                myTween.waitEnd();

                myTween.Destroy(false);
            }


        }
    }

    public void waitEnd() {
        if (this.isPlaying) {
            try {
                latchEvent.await();
            }catch (InterruptedException a) {
                a.printStackTrace();
            }
        }
    }





}
