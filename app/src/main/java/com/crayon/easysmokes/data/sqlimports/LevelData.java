package com.crayon.easysmokes.data.sqlimports;

import android.content.ContentValues;

/**
 * Created by Jay on 7/12/2016.
 */

public class LevelData extends SQLInsertionHelper {

    public static final String TABLE_NAME = "levels";
    public static final String ATT_ID = "_id";
    public static final String ATT_NAME = "levelName";

    @Override
    public void execute(int count, String column, ContentValues values) {
        switch (count) {
            case 0:
                values.put(ATT_ID, column);
                break;
            case 1:
                values.put(ATT_NAME, column);
                break;
        }
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }
}
