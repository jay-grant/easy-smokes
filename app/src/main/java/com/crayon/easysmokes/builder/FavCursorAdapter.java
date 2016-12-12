package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crayon.easysmokes.FavouritesActivity;
import com.crayon.easysmokes.R;
import com.crayon.easysmokes.SwipeLauncher;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.FavData;
import com.crayon.easysmokes.data.sqlimports.NadeData;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.Nade;

import org.w3c.dom.Text;

/**
 * Created by Jay on 9/12/2016.
 */

public class FavCursorAdapter extends CursorAdapter {

    public FavCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.button_favourite, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        RelativeLayout buttonMain = (RelativeLayout) view.findViewById(R.id.favnade_button_main);
        TextView textMain = (TextView) view.findViewById(R.id.favnade_textview);
        TextView textSecond = (TextView) view.findViewById(R.id.favnade_secondarytext);
        textSecond.setVisibility(View.VISIBLE);
        ImageView line = (ImageView) view.findViewById(R.id.favnade_button_line);

        String nadeID = cursor.getString(cursor.getColumnIndex(NadeData.ATT_ID));
        String nadeName = cursor.getString(cursor.getColumnIndex(NadeData.ATT_NAME));
        String nadeSide = cursor.getString(cursor.getColumnIndex(NadeData.ATT_SIDE));
        String nadeLevel = cursor.getString(cursor.getColumnIndex(NadeData.ATT_SIDE));

        if (nadeSide.equals("t")) {
            line.setBackgroundResource(R.drawable.shape_gradient_fade_t);
        }
        if (nadeSide.equals("ct")) {
            line.setBackgroundResource(R.drawable.shape_gradient_fade_ct);
        }

        textMain.setText(AestheticFormat.vapour(nadeName));

        listener(context, view, nadeID, buttonMain);


    }

    private void listener (Context context, View view, String nadeID, View item) {
        item.setOnTouchListener(new NadeTouchListener(nadeID));
    }
}
