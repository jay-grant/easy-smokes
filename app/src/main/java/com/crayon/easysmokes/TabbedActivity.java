package com.crayon.easysmokes;

import android.app.TabActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.SideData;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.TabBuilder;

import java.util.List;

public class TabbedActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Bundle bundle = getIntent().getExtras();
        String levelString = (String) bundle.get(String.valueOf(BundleKey.LEVEL));

        DataBase database = new DataBase(this);
        SQLiteDatabase reader = database.getReadableDatabase();
        List<String> sideIDs = database.getAllAttributes(SideData.TABLE_NAME, SideData.ATT_ID);
        List<String> sideNames = database.getAllAttributes(SideData.TABLE_NAME, SideData.ATT_NAME);

        if (sideIDs.size() < 2) {
            return;
        }

        TabHost tabHost = this.getTabHost();
        TabBuilder[] tabList = {
                new TabBuilder(sideNames.get(0), sideIDs.get(0),
                        this.getResources().getColor(R.color.tYellow)),
                new TabBuilder(sideNames.get(1), sideIDs.get(1),
                        this.getResources().getColor(R.color.ctBlue))
        };
        buildTab(tabHost, tabList, levelString);

    }

    private void buildTab (TabHost tabHost, TabBuilder[] tabs, String level) {

        int i = 0;

        for (TabBuilder tabBuilder :
                tabs) {
            TabHost.TabSpec tab = tabHost.newTabSpec(tabBuilder.getDispName());
            tab.setIndicator(tabBuilder.getDispName());


            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(BundleKey.TAB), tabBuilder.getSide());
            bundle.putString(String.valueOf(BundleKey.LEVEL), level);

            Intent tabIntent = new Intent(this, TabContent.class);
            tabIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            tabIntent.putExtras(bundle);
            tab.setContent(tabIntent);
            tabHost.addTab(tab);

            setTabStyle(tabHost, i, tabBuilder.getColour());
            i += 1;
        }

    }

    private void setTabStyle(TabHost tabHost, int tabPos, int colour) {
        TextView textView = (TextView) tabHost.getTabWidget()
                .getChildAt(tabPos).findViewById(android.R.id.title);
        textView.setTextColor(colour);
        tabHost.getTabWidget().setBackgroundResource(R.color.colorPrimaryDark);
    }

}
