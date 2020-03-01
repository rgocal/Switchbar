package com.gocalsd.switchbar;

import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.core.graphics.ColorUtils;

public class ColorUtil {

    public static boolean isDark(int color){
        return ColorUtils.calculateLuminance(color) <0.5;
    }

    public static int getLighterShadeColor(int c){
        float[] hsv = new float[3];
        int color = c;
        Color.colorToHSV(color, hsv);
        hsv[2] *= 1.35f;
        color = Color.HSVToColor(hsv);
        return color;
    }

    public static int getDarkerShadeColor(int c){
        float[] hsv = new float[3];
        int color = c;
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.80f;
        color = Color.HSVToColor(hsv);
        return color;
    }

    public static ColorStateList colorToStateList(@ColorInt int color, @ColorInt int disabledColor) {
        return new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{},
                },
                new int[]{
                        disabledColor,
                        disabledColor,
                        color,
                });
    }


}
