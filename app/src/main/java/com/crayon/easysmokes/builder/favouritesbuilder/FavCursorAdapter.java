package com.crayon.easysmokes.builder.favouritesbuilder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.builder.AestheticFormat;
import com.crayon.easysmokes.builder.NadeTouchListener;
import com.crayon.easysmokes.data.sqlimports.NadeData;

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
