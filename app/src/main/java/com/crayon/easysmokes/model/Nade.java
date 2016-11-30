package com.crayon.easysmokes.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Nade {

    private LevelName levelName;
    private String id;
    private String name;
    private SideType side;
    private NadeType nadeType = NadeType.SMOKE;


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
        SharedPreferences sharedPrefs = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedKey.FAVS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(id, true);
        editor.apply();
    }

    public void removeFav(Context context) {
        SharedPreferences sharedPrefs = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedKey.FAVS), Context.MODE_PRIVATE);
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

}
