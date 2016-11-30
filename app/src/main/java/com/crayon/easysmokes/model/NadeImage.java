package com.crayon.easysmokes.model;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NadeImage {

    String[] urls;
    String id;
    List<Drawable> drawables = new ArrayList<Drawable>();


    public NadeImage(String id, String[] urls) {
        this.id = id;
        this.urls = urls;
    }

    public List<Drawable> getDrawables() {

        try {
            for (String url :
                    urls) {
                InputStream inputStream = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(inputStream, "src name");
                drawables.add(d);
            }
        } catch (Exception e) {
            return null;
        }
        return drawables;
    }

    public String[] getUrls() {
        return urls;
    }

    public ArrayList<String> getUrlArray() {

        ArrayList<String> urlArray = new ArrayList<String>();

        for (String url :
                urls) {
            urlArray.add(url);
        }
        return urlArray;
    }

    public String getId() {
        return id;
    }
}

