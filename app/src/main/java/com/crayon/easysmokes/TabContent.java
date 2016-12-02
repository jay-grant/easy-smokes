package com.crayon.easysmokes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crayon.easysmokes.animation.NotificationHelper;
import com.crayon.easysmokes.builder.NadeButton;
import com.crayon.easysmokes.model.BundleKey;

public class TabContent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tab);



        Bundle bundle = getIntent().getExtras();
        String level = bundle.getString(String.valueOf(BundleKey.LEVEL));
        String side = bundle.getString(String.valueOf(BundleKey.TAB));

        LinearLayout layout = (LinearLayout) findViewById(R.id.tabContent_ScrollView);
        TextView textView = (TextView) findViewById(R.id.tabContent_notification_TextView);

        NotificationHelper notiHelp = new NotificationHelper(textView);

        new NadeButton(level, side, this, layout, notiHelp);
    }
}
