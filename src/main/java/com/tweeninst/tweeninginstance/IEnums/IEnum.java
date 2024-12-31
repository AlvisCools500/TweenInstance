package com.tweeninst.tweeninginstance.IEnums;

import com.tweeninst.tweeninginstance.instances.World;

import java.util.ArrayList;

public class IEnum {
    public enum Properties {
        Position,
        Size,
        ZIndex,
        Color,
        Transparency,
        Rotation,
        AnchorPoint,
        Text,
        TextFont,
        TextColor,
        TextSize,
        TextScaled,
        SizeCorner,
        SizeRatio,
    }

    public enum IsA {
        Frame,
        TextLabel,
        World,
        Stroke,
        RatioConstraint,
        DebugShape,
        Non,
    }

    public static boolean isRenderable(IsA v) {
        ArrayList<IEnum.IsA> allowList = new ArrayList<>();
        if (v == IsA.Frame || v == IsA.TextLabel || v == IsA.World) {
            return true;
        }

        return false;
    }
}
