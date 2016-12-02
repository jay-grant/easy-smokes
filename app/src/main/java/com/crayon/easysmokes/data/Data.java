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
            new Nade(LevelName.MIRAGE, "m1", "ayylmao", SideType.U),
            new Nade(LevelName.MIRAGE, "m4", "ct_bedroom", SideType.T)
    };

    private static NadeImage[] nadeImages = {
            new NadeImage("d1", new String[]{"http://boredomtherapy.com/wp-content/uploads/2014/05/bad-taxidemy-7.jpg",
                    "http://www.oddee.com/_media/imgs/articles2/a99361_crappy-taxidermy-2.jpg",
                    "http://2.bp.blogspot.com/-qxPwhEq6KZk/UcmlkHvz_eI/AAAAAAAACq4/RU_kYFehS7Y/s1600/fox1.JPG"
            }),

            new NadeImage("m4", new String[]{
                    "http://2.bp.blogspot.com/-qxPwhEq6KZk/UcmlkHvz_eI/AAAAAAAACq4/RU_kYFehS7Y/s1600/fox1.JPG"
            }),

            new NadeImage("m1", new String[] {
                    "http://www.oddee.com/_media/imgs/articles2/a99361_crappy-taxidermy-2.jpg"
            })
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
