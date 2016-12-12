package com.crayon.easysmokes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.crayon.easysmokes.builder.LevelCursorAdapter;
import com.crayon.easysmokes.builder.NadeCursorAdapter;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.LevelData;
import com.crayon.easysmokes.data.sqlimports.NadeData;

public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        ListView listView = (ListView) findViewById(R.id.LevelsActivity_ListView);

        DataBase database = new DataBase(this);
        SQLiteDatabase readable = database.getReadableDatabase();
        Cursor cursor = readable.rawQuery("SELECT * FROM " + LevelData.TABLE_NAME, null);
        LevelCursorAdapter adapter = new LevelCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
    }

}
