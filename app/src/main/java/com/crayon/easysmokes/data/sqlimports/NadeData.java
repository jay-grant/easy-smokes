package com.crayon.easysmokes.data.sqlimports;

import android.content.ContentValues;

/**
 * Created by Jay on 7/12/2016.
 */

public class NadeData extends SQLInsertionHelper {

    public static final String TABLE_NAME = "nades";
    public static final String ATT_NAME = "nadeName";
    public static final String ATT_ID = "_id";
    public static final String ATT_LEVEL = "nadeLevel";
    public static final String ATT_SIDE = "nadeSide";
    public static final String ATT_DEMOCOUNT = "demoCount";

    @Override
    public void execute(int count, String column, ContentValues values) {
        switch (count) {
            case 0:
                values.put(ATT_ID, column);
                break;
            case 1:
                values.put(ATT_NAME, column);
            case 2:
                values.put(ATT_LEVEL, column);
                break;
            case 3:
                values.put(ATT_SIDE, column);
                break;
            case 4:
                values.put(ATT_DEMOCOUNT, Integer.parseInt(column));
                break;
        }
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }
}
