package com.geekcoders.payingguest.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by admin on 10/11/17.
 */

public class Fonts {

    private static Typeface typeFaceOpenSansLight;
    private static Typeface typeFaceOpenSansRegular;
    private static Typeface typeFaceOpenSansBold;
    private static Typeface typeFaceOpenSansSemiBold;


    public static void setLightFont(Context context,TextView textView) {
        typeFaceOpenSansLight = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Light.ttf");
        textView.setTypeface(typeFaceOpenSansLight);
    }

    public static void setRegularFont(Context context,TextView textView) {
        typeFaceOpenSansRegular = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
        textView.setTypeface(typeFaceOpenSansRegular);
    }

    public static void setBoldFont(Context context, TextView textView) {
        typeFaceOpenSansBold = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Bold.ttf");
        textView.setTypeface(typeFaceOpenSansBold);
    }

    public static void setSemiBoldFont(Context context, TextView textView) {
        typeFaceOpenSansSemiBold = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Semibold.ttf");
        textView.setTypeface(typeFaceOpenSansSemiBold);
    }
}
