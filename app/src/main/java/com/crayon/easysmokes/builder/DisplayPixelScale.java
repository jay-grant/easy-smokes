package com.crayon.easysmokes.builder;

import android.content.Context;

public class DisplayPixelScale {

    public int getDP(Integer integer, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (integer * scale + 0.5f);
    }
}
