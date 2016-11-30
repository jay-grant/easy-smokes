package com.crayon.easysmokes.builder;

import android.content.Context;
import android.util.Log;

import com.crayon.easysmokes.model.TouchImageView;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.bitmap.IonBitmapCache;

public class GetNadeImageHelper {

    public static void getImages(Context context, TouchImageView touchImageView, String url) {

        Ion.with(context)
                .load(url)
                .setLogging("IMAGE", Log.VERBOSE)
                .withBitmap()
                .intoImageView(touchImageView);
    }
}
