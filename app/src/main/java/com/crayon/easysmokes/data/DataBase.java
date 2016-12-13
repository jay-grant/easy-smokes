package com.crayon.easysmokes.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.data.sqlimports.DemoData;
import com.crayon.easysmokes.data.sqlimports.LevelData;
import com.crayon.easysmokes.data.sqlimports.NadeData;
import com.crayon.easysmokes.data.sqlimports.SQLInsertionHelper;
import com.crayon.easysmokes.data.sqlimports.SideData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 5/12/2016.
 */

public class DataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "Nades.db";

    public DataBase(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        System.out.println("ENTRED onCreate METHOD FOR SQLite TESTING");
        createTables(database);
        importDefaultData(database);
        importNadeData(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + DemoData.TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + NadeData.TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + LevelData.TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + SideData.TABLE_NAME);
        onCreate(database);
    }

    private void createTables(SQLiteDatabase database) {
        List<String> blocks = Data.readBlocks(R.raw.create_tables, context);

        for (String block :
                blocks) {
            database.execSQL(block);
        }
    }

    private void importDefaultData(SQLiteDatabase database) {
        // Import Levels and Sides
        importData(database, new LevelData());
        importData(database, new SideData());
    }

    private void importNadeData(SQLiteDatabase database) {
        // Import Nades
        importData(database, new NadeData());

        // Import PreExisting Demos
        List<String> lines = Data.readRows(R.raw.demos, context);
        DemoData demoInsert = new DemoData();

        for (String row :
                lines) {
            ContentValues contentValues = new ContentValues();
            int i = 0;
            for (String col :
                    row.split(",")) {
                demoInsert.execute(i, col, contentValues);
                i++;
            }
            database.insert(DemoData.TABLE_NAME, null, contentValues);
        }
    }

    private void importData(SQLiteDatabase database, SQLInsertionHelper insertionHelper) {

        int file = context.getResources().getIdentifier(insertionHelper.tableName(), "raw", context.getApplicationContext().getPackageName());
        List<String> rowStrings = Data.readRows(file, context);
        ContentValues contentValues = new ContentValues();

        int counter;
        for (String row :
                rowStrings) {

            counter = 0;
            for (String column :
                    row.split(",")) {
                insertionHelper.execute(counter, column, contentValues);
                counter++;
            }
            database.insert(insertionHelper.tableName(), null, contentValues);
            contentValues.clear();
        }
    }

    public static String setString (String what, String value) {
        return "SET " + what + "=" + value;
    }

    public static String selectRowString (String table) {
        return "SELECT * FROM " + table;
    }

    public static String selectString(String what, String table) {
        return "SELECT " + what + " FROM " + table;
    }

    public static String whereString(String a, String b) {
        return "WHERE " + a + "=" + b;
    }

    public static String toSQLString(String s) {
        return "\"" + s + "\"";
    }

    /**
     * Returns a List of every value of a particular Attribute given a Table Name
     *
     * @return List of Strings
     */
    public List<String> getAllAttributes(String tableName, String attribute) {
        List<String> levelNames = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor row = database.rawQuery(selectRowString(tableName), null);
        row.moveToFirst();

        while (!row.isAfterLast()) {
            levelNames.add(row.getString(row.getColumnIndex(attribute)));
            row.moveToNext();
        }

        row.close();
        return levelNames;
    }

    public List<String> selectFromWhere(String select, String from, String where, String equals) {
        List<String> levelNames = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String query = selectRowString(from)
                + " " + whereString(where, equals);
        Cursor row = database.rawQuery(query, null);
        row.moveToFirst();

        while (!row.isAfterLast()) {
            levelNames.add(row.getString(row.getColumnIndex(select)));
            row.moveToNext();
        }

        row.close();
        return levelNames;
    }

    public static String joinOn(String table1, String att1, String table2, String att2) {
        return " INNER JOIN " + table2 + " ON " + table1 + "." + att1 + "=" + table2 + "." + att2;
    }

    public boolean isFavourited(String nadeID) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor row = database.rawQuery("SELECT * FROM " + NadeData.TABLE_NAME
                + " WHERE " + NadeData.ATT_ID + "=" + toSQLString(nadeID)
                + " AND " + NadeData.ATT_FAVORDER + " IS NOT NULL", null);
        boolean exists = !(row.getCount() <= 0);
        row.close();
        database.close();
        return exists;
    }

    public void addFavourite(String nadeID) {
        SQLiteDatabase database = this.getWritableDatabase();
        int count = database.rawQuery("SELECT * FROM " + NadeData.TABLE_NAME
                + " WHERE " + NadeData.ATT_FAVORDER + " IS NOT NULL", null).getCount();
        database.execSQL("UPDATE " + NadeData.TABLE_NAME
                + " SET " + NadeData.ATT_FAVORDER + "=" + (count + 1)
                + " WHERE " + NadeData.ATT_ID + "=" + toSQLString(nadeID));
        database.close();
    }

    public void removeFavourite(String nadeID) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("UPDATE " + NadeData.TABLE_NAME
                + " SET " + NadeData.ATT_FAVORDER + "=NULL"
                + " WHERE " + NadeData.ATT_ID + "=" + toSQLString(nadeID));
        database.close();
    }

    public int countDemos(String nadeID) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = selectRowString(DemoData.TABLE_NAME)
                + " " + whereString(DemoData.ATT_NADE, toSQLString(nadeID));
        Cursor cursor = database.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count;
    }

    public Cursor getFavourites(Context context) {

        SQLiteDatabase reader = this.getReadableDatabase();
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT * FROM " + NadeData.TABLE_NAME +" WHERE " + NadeData.ATT_FAVORDER + " IS NOT NULL")
                .append(" ORDER BY " + NadeData.ATT_FAVORDER);

        SharedPreferences sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(SharedPrefsKey.FAV_SETTINGS.toString(), Context.MODE_PRIVATE);
        String orderSetting = sharedPreferences
                .getString(SharedPrefsKey.FAV_ORDER.toString(), FavouriteOrderKeys.NEWEST.toString());
        String groupSetting = sharedPreferences
                .getString(SharedPrefsKey.FAV_GROUP.toString(), FavouriteGroupKeys.NONE.toString());
        if (orderSetting.equals(FavouriteOrderKeys.OLDEST.toString())) {
            queryBuilder.append(" DESC");
        }
        if (groupSetting.equals(FavouriteGroupKeys.LEVEL.toString())) {
            queryBuilder.append(", " + NadeData.ATT_LEVEL);
        } else if (groupSetting.equals(FavouriteGroupKeys.SIDE.toString())) {
            queryBuilder.append(", " + NadeData.ATT_SIDE);
        }

        System.out.println("QUERY: " + queryBuilder.toString());

        Cursor cursor = reader.rawQuery(queryBuilder.toString(), null);
        return cursor;
    }
}
