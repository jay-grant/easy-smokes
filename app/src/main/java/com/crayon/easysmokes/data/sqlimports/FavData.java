package com.crayon.easysmokes.data.sqlimports;

import android.content.ContentValues;

/**
 * Created by Jay on 7/12/2016.
 */

public class FavData extends SQLInsertionHelper {

    public static final String TABLE_NAME = "favs";
    public static final String ATT_NADE = "_id";
    public static final String ATT_ORDER = "orderCounter";

    @Override
    public void execute(int count, String column, ContentValues values) {
        switch (count) {
            case 0:
                values.put(ATT_NADE, column);
                break;
            case 1:
                values.put(ATT_ORDER, column);
                break;
        }
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }
}
