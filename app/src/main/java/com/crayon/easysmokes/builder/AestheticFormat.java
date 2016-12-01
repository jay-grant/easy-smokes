package com.crayon.easysmokes.builder;

/**
 * Created by Jay on 2/12/2016.
 */

public class AestheticFormat {

    public static String vapour(String string) {
        StringBuilder aesthetic = new StringBuilder(string.length() * 2);
        char[] chars = string.toCharArray();
        for (char c :
                chars) {
            aesthetic.append(c);
            aesthetic.append(' ');
        }
        aesthetic.deleteCharAt(aesthetic.length() - 1);
        return aesthetic.toString();
    }
}
