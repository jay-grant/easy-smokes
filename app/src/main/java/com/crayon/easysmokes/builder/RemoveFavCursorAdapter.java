package com.crayon.easysmokes.builder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crayon.easysmokes.FavouritesActivity;
import com.crayon.easysmokes.R;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.NadeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 12/12/2016.
 */

public class RemoveFavCursorAdapter extends CursorAdapter {

    List<String> queue;

    public RemoveFavCursorAdapter(Context context, Cursor cursor, List<String> queue) {
        super(context, cursor, 0);
        this.queue = queue;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.button_favourite, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        boolean checked = false;
        final RelativeLayout buttonMain = (RelativeLayout) view.findViewById(R.id.favnade_button_main);
        final TextView textMain = (TextView) view.findViewById(R.id.favnade_textview);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.favnade_checkbox);
        checkBox.setVisibility(View.VISIBLE);
        final ImageView line = (ImageView) view.findViewById(R.id.favnade_button_line);

        final String nadeID = cursor.getString(cursor.getColumnIndex(NadeData.ATT_ID));
        String nadeName = cursor.getString(cursor.getColumnIndex(NadeData.ATT_NAME));
        String nadeSide = cursor.getString(cursor.getColumnIndex(NadeData.ATT_SIDE));
        String nadeLevel = cursor.getString(cursor.getColumnIndex(NadeData.ATT_SIDE));
        if (nadeSide.equals("t")) {
            line.setBackgroundResource(R.drawable.shape_gradient_fade_t);
        }
        if (nadeSide.equals("ct")) {
            line.setBackgroundResource(R.drawable.shape_gradient_fade_ct);
        }
        if (nadeSide.equals("a")) {
            line.setBackgroundResource(R.drawable.shape_gradient_fade_grey);
        }
        textMain.setText(AestheticFormat.vapour(nadeName));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    queue.add(nadeID);
                } else {
                    queue.remove(nadeID);
                }
            }
        });

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCheck(checkBox, nadeID);
            }
        });

    }

    private void handleCheck(CheckBox checkBox, String nadeID) {
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
            queue.remove(nadeID);
        } else {
            checkBox.setChecked(true);
            queue.add(nadeID);
        }
    }
}

