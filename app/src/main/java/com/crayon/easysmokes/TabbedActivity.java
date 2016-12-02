package com.crayon.easysmokes;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.SideType;
import com.crayon.easysmokes.model.TabBuilder;

public class TabbedActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Bundle bundle = getIntent().getExtras();
        String levelString = (String) bundle.get(String.valueOf(BundleKey.LEVEL));

        TabHost tabHost = this.getTabHost();

        TabBuilder[] tabList = {
                new TabBuilder("Terrorist", SideType.T,
                        getResources().getColor(R.color.tYellow)),
                new TabBuilder("Counter-Terrorist", SideType.CT,
                        getResources().getColor(R.color.ctBlue))
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
            bundle.putString(String.valueOf(BundleKey.TAB), tabBuilder.getSide().toString());
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
    }

}
