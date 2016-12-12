package com.crayon.easysmokes.data.sqlimports;

import android.content.ContentValues;

/**
 * Created by Jay on 6/12/2016.
 */

public abstract class SQLInsertionHelper {

    public abstract void execute(int count, String column, ContentValues values);

    public abstract String tableName();
}
