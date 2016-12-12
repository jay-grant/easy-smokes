package com.crayon.easysmokes;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.crayon.easysmokes.animation.NotificationHelper;
import com.crayon.easysmokes.builder.LevelCursorAdapter;
import com.crayon.easysmokes.builder.NadeCursorAdapter;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.LevelData;
import com.crayon.easysmokes.data.sqlimports.NadeData;
import com.crayon.easysmokes.model.BundleKey;

import java.sql.SQLData;

public class TabContent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tab);

        Bundle bundle = getIntent().getExtras();
        String level = bundle.getString(String.valueOf(BundleKey.LEVEL));
        String side = bundle.getString(String.valueOf(BundleKey.TAB));

        ListView listView = (ListView) findViewById(R.id.tabContent_ListView);
        TextView textView = (TextView) findViewById(R.id.tabContent_notification_TextView);

        NotificationHelper notiHelp = new NotificationHelper(textView);

        DataBase database = new DataBase(this);
        SQLiteDatabase reader = database.getReadableDatabase();
        String query = DataBase.selectRowString(NadeData.TABLE_NAME)
                + " " + DataBase.whereString(NadeData.ATT_LEVEL, DataBase.toSQLString(level))
                + " AND (" + NadeData.ATT_SIDE + "=" + DataBase.toSQLString(side)
                + " OR " + NadeData.ATT_SIDE + "=" + DataBase.toSQLString("a") + ")" ;
        Cursor cursor = reader.rawQuery(query, null);
        NadeCursorAdapter adapter = new NadeCursorAdapter(this, cursor, notiHelp);
        listView.setAdapter(adapter);
    }
}
