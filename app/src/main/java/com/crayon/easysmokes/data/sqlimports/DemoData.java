package com.crayon.easysmokes.data.sqlimports;

import android.content.ContentValues;

/**
 * Created by Jay on 7/12/2016.
 */

public class DemoData extends SQLInsertionHelper {

    public static final String TABLE_NAME = "demos";
    public static final String ATT_NADE = "_id";
    public static final String ATT_ID = "demoNumber";
    public static final String ATT_TITLE = "title";
    public static final String ATT_DESC = "description";
    public static final String ATT_IMG = "imgUrl";

    @Override
    public void execute(int count, String column, ContentValues values) {
        switch (count) {
            case 0:
                values.put(ATT_NADE, column);
                break;
            case 1:
                values.put(ATT_ID, Integer.parseInt(column));
                break;
            case 2:
                values.put(ATT_TITLE, column);
                break;
            case 3:
                values.put(ATT_DESC, column);
                break;
            case 4:
                values.put(ATT_IMG, column);
                break;
        }
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }
}
