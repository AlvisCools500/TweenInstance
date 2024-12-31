package com.tweeninst.tweeninginstance.IEnums;

public class EasingService {

    public enum EasingStyle {
        LINEAR,
        SINE,
        EXPONENTIAL,
        EXPONENTE,
        CUBIC,
        ELASTIC,
        QUAD,
        BACK,
        BOUNCE,
        SEXTIC,
        SEPTIC,
        OCTIC,
        QUARTIC,
        QUINTIC,
    }

    public enum EasingDirection {
        IN,
        OUT,
        INOUT,
    }

    // Get Alpha Value
    public double GetValue(double alpha, EasingStyle style, EasingDirection direction) {
        double p = alpha;

        switch (style) {
            case QUINTIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutQuintic(p);
                    case IN:
                        return easings.InQuintic(p);
                    case OUT:
                        return easings.OutQuintic(p);
                }
            case QUARTIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutQuartic(p);
                    case IN:
                        return easings.InQuartic(p);
                    case OUT:
                        return easings.OutQuartic(p);
                }
            case OCTIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutOctic(p);
                    case IN:
                        return easings.InOctic(p);
                    case OUT:
                        return easings.OutOctic(p);
                }
            case SEPTIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutSeptic(p);
                    case IN:
                        return easings.InSeptic(p);
                    case OUT:
                        return easings.OutSeptic(p);
                }
            case SEXTIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutSextic(p);
                    case IN:
                        return easings.InSextic(p);
                    case OUT:
                        return easings.OutSextic(p);
                }
            case BOUNCE:
                switch (direction) {
                    case INOUT:
                        return easings.InOutBounce(p);
                    case IN:
                        return easings.InBounce(p);
                    case OUT:
                        return easings.OutBounce(p);
                }
            case QUAD:
                switch (direction) {
                    case INOUT:
                        return easings.InOutQuadratic(p);
                    case IN:
                        return easings.InQuadratic(p);
                    case OUT:
                        return easings.OutQuadratic(p);
                }
            case CUBIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutCubic(p);
                    case IN:
                        return easings.InCubic(p);
                    case OUT:
                        return easings.OutCubic(p);
                }
            case ELASTIC:
                switch (direction) {
                    case INOUT:
                        return easings.InOutElastic(p);
                    case IN:
                        return easings.InElastic(p);
                    case OUT:
                        return easings.OutElastic(p);
                }
            case BACK:
                switch (direction) {
                    case INOUT:
                        return easings.InOutBack(p);
                    case IN:
                        return easings.InBack(p);
                    case OUT:
                        return easings.OutBack(p);
                }
            case EXPONENTIAL:
                switch (direction) {
                    case INOUT:
                        return easings.InOutExponent2(p);
                    case IN:
                        return easings.InExponent2(p);
                    case OUT:
                        return easings.OutExponent2(p);
                }
            case SINE:
                switch (direction) {
                    case INOUT:
                        return easings.InOutSine(p);
                    case IN:
                        return easings.InSine(p);
                    case OUT:
                        return easings.OutSine(p);
                }
            case LINEAR:
                return alpha;
            default:
                return alpha;
        }
    }
}
