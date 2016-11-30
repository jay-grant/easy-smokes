package com.crayon.easysmokes.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.crayon.easysmokes.model.LevelName;
import com.crayon.easysmokes.model.Nade;
import com.crayon.easysmokes.model.NadeImage;
import com.crayon.easysmokes.model.SideType;

public class Data {

    private static Nade[] nades = {
            new Nade(LevelName.DUST2, "d1", "ct_bedroom", SideType.CT),
            new Nade(LevelName.DUST2, "d2", "ct_bedroom", SideType.U),
            new Nade(LevelName.DUST2, "d3", "ct_bedroom", SideType.T),
            new Nade(LevelName.DUST2, "d4", "ct_bedroom", SideType.T),
            new Nade(LevelName.INFERNO, "i1", "ct_bedroom", SideType.CT),
            new Nade(LevelName.INFERNO, "i2", "ct_bedroom", SideType.T),
            new Nade(LevelName.INFERNO, "i3", "ct_bedroom", SideType.T),
            new Nade(LevelName.CACHE, "c1", "ct_bedroom", SideType.T),
            new Nade(LevelName.CACHE, "c2", "ct_bedroom", SideType.T),
            new Nade(LevelName.MIRAGE, "m1", "ct_bedroom", SideType.CT),
            new Nade(LevelName.MIRAGE, "m2", "ct_bedroom", SideType.CT),
            new Nade(LevelName.MIRAGE, "m3", "ct_bedroom", SideType.U),
            new Nade(LevelName.MIRAGE, "m4", "ct_bedroom", SideType.T),
            new Nade(LevelName.MIRAGE, "m5", "ct_bedroom", SideType.T),
            new Nade(LevelName.MIRAGE, "m6", "ct_bedroom", SideType.T),
            new Nade(LevelName.MIRAGE, "m7", "ct_bedroom", SideType.T),
    };

    private static NadeImage[] nadeImages = {
            new NadeImage("d1", new String[]{"ayylmao.jpg", "gucci", "the_bee_movie"})
    };

    public static Nade[] getNadeList() {return nades;}

    public static NadeImage getNadeImage(String id) {

        NadeImage nadeImage = null;

        for (NadeImage nade :
                nadeImages) {
            if (id.equals(nade.getId())){
                nadeImage = nade;
            }
        }
        return nadeImage;
    }

}
