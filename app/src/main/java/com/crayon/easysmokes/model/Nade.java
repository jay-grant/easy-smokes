package com.crayon.easysmokes.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Nade {

    private LevelName levelName;
    private String id;
    private String name;
    private SideType side;
    private NadeType nadeType = NadeType.SMOKE;
    private int favOrder = 0;

    public Nade (LevelName levelName, String id, String name, SideType side) {
        this.levelName = levelName;
        this.id = id;
        this.name = name;
        this.side = side;
    }

    public Nade (LevelName levelName, String id, String name, SideType side, NadeType nadeType) {
        this.levelName = levelName;
        this.id = id;
        this.name = name;
        this.side = side;
        this.nadeType = nadeType;
    }

    public void addFav(Context context) {
        SharedPreferences counter = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedPrefsKey.FAVS_COUNT), Context.MODE_PRIVATE);
        SharedPreferences sharedPrefs = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedPrefsKey.FAVS), Context.MODE_PRIVATE);
        int count = sharedPrefs.getAll().size();
        count++;
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(id, count);
        editor.apply();
    }

    public void removeFav(Context context) {
        SharedPreferences sharedPrefs = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedPrefsKey.FAVS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(id);
        editor.apply();
    }

    public LevelName getLevelName() {
        return levelName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SideType getSide() {
        return side;
    }

    public NadeType getNadeType() {
        return nadeType;
    }

    public int getFavOrder() {
        return favOrder;
    }

    public void setFavOrder(int favOrder) {
        this.favOrder = favOrder;
    }
}
