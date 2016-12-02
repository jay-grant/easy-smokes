package com.crayon.easysmokes.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.crayon.easysmokes.R;

/**
 * Created by Jay on 2/12/2016.
 */

public class AdvancedSwitch {

    public AdvancedSwitch(String title, String description, final Context context, LinearLayout container) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View template = layoutInflater.inflate(R.layout.switch_advanced, null);
        Switch androidSwitch = (Switch) template.findViewById(R.id.switch_primary);
        TextView descriptionText = (TextView) template.findViewById(R.id.switch_description_text);

        androidSwitch.setText(title);
        descriptionText.setText(description);

        container.addView(template);
    }
}
