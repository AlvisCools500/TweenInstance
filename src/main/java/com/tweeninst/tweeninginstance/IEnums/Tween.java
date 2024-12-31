package com.tweeninst.tweeninginstance.IEnums;

import com.tweeninst.tweeninginstance.instances.Instance;
import com.tweeninst.tweeninginstance.mainMod;
import com.tweeninst.tweeninginstance.vectors.UDim;
import com.tweeninst.tweeninginstance.vectors.UDim2;
import com.tweeninst.tweeninginstance.vectors.VectorDouble2D;
import javafx.animation.AnimationTimer;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class Tween {
    double duration;
    private EasingService.EasingStyle easeStyle;
    private EasingService.EasingDirection easeDir;
    private Instance targetinst;
    private AnimationTimer timer;
    public HashProperty propertyTarg = new HashProperty();
    boolean isPlaying = false;
    CountDownLatch latchEvent = new CountDownLatch(1);

    public Tween(Instance targetinst, double Time, EasingService.EasingStyle EaseStyle, EasingService.EasingDirection EaseDirection) {
        this.easeStyle = EaseStyle;
        this.easeDir = EaseDirection;
        this.targetinst = targetinst;
        this.duration = Time;
    }

    private double basicAdds_Double(double startVal, double endVal, double alpha) {
        return startVal + (endVal - startVal) * alpha;
    }

    private int basicAdds_Int(int startVal, int endVal, double alpha) {
        return (int) basicAdds_Double(startVal, endVal, alpha);
    }

    public void play() {
        if (!this.isPlaying) {
            this.isPlaying = true;

            HashMap<IEnum.Properties, Object> propStart = new HashMap<>();
            HashMap<IEnum.Properties, Object> propEnd = propertyTarg;

            HashMap<IEnum.Properties, Object> propertyTable = targetinst.properties;

            for (var v : propEnd.entrySet()) {
                IEnum.Properties index = v.getKey();

                if (propertyTable.get(index) != null) {
                    propStart.put(index, propertyTable.get(index));
                }
            }

            long startClock = mainMod.GetLongClock();

            EasingService easeServ = new EasingService();

            timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    long TargetClock = mainMod.GetLongClock();
                    double ResClock = (double) (TargetClock - startClock) / 1e9;
                    double Alpha = Math.clamp(ResClock/duration, 0, 1);
                    double easedAlpha = easeServ.GetValue(Alpha, easeStyle, easeDir);

                    for (var v : propEnd.entrySet()) {
                        IEnum.Properties index = v.getKey();

                        String valType = v.getValue().getClass().getSimpleName();

                        // UDim2 Support
                        if (valType.equals("UDim2")) {

                            UDim2 targUDim2 = (UDim2) v.getValue();
                            UDim2 startUDim2 = (UDim2) propStart.get(index);

                            propertyTable.put(index, new UDim2(
                                    basicAdds_Double(startUDim2.x.scale, targUDim2.x.scale, easedAlpha),
                                    basicAdds_Double(startUDim2.x.offset, targUDim2.x.offset, easedAlpha),
                                    basicAdds_Double(startUDim2.y.scale, targUDim2.y.scale, easedAlpha),
                                    basicAdds_Double(startUDim2.y.offset, targUDim2.y.offset, easedAlpha)
                            ));
                        }else if (valType.equals("UDim")) {
                            UDim targUDim = (UDim) v.getValue();
                            UDim startUDim = (UDim) propStart.get(index);

                            propertyTable.put(index, new UDim(
                                    basicAdds_Double(startUDim.scale, targUDim.scale, easedAlpha),
                                    basicAdds_Double(startUDim.offset, targUDim.offset, easedAlpha)
                            ));
                        }else if (valType.equals("Double")) {
                            double targDoub = (double) v.getValue();
                            double startDoub = (double) propStart.get(index);

                            propertyTable.put(index, basicAdds_Double(startDoub, targDoub, easedAlpha));
                        }else if (valType.equals("Integer")) {
                            int targInt = (int) v.getValue();
                            int startInt = (int) propStart.get(index);

                            propertyTable.put(index, basicAdds_Int(startInt, targInt, easedAlpha));
                        }else if (valType.equals("VectorDouble2D")) {
                            VectorDouble2D targVect = (VectorDouble2D) v.getValue();
                            VectorDouble2D startVect = (VectorDouble2D) propStart.get(index);

                            propertyTable.put(index, new VectorDouble2D(
                                    basicAdds_Double(startVect.x, targVect.x, easedAlpha),
                                    basicAdds_Double(startVect.y, targVect.y, easedAlpha)
                            ));
                        }else {
                            System.out.println(valType + " is not available");
                        }
                    }

                    if (Alpha >= 1) {
                        latchEvent.countDown();
                        timer.stop();
                    }
                }
            };



            timer.start();
        }

    }

    public void stop() {
        if (this.isPlaying && this.timer != null) {
            this.timer.stop();
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

    public void Destroy(boolean waitEnd) {

        if (this.isPlaying) {
            if (waitEnd) {
                this.stop();
            }else {
                this.waitEnd();
            }
        }

        this.propertyTarg.clear();
        this.propertyTarg = null;
        this.isPlaying = false;
        this.easeStyle = null;
        this.easeDir = null;
        this.targetinst = null;
        this.latchEvent = null;
        this.duration = 0;

        System.gc();
    }
}
