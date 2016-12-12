package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.TabbedActivity;
import com.crayon.easysmokes.data.sqlimports.LevelData;
import com.crayon.easysmokes.model.BundleKey;

/**
 * Created by Jay on 7/12/2016.
 */

public class LevelCursorAdapter extends CursorAdapter {

    private final int BUTTON_ALPHA = 80;
    private final int BUTTON_ALPHA_PRESS = 225;

    public LevelCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.button_level, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final String levelID = cursor.getString(cursor.getColumnIndex(LevelData.ATT_ID));
        final String levelNameString = cursor.getString(cursor.getColumnIndex(LevelData.ATT_NAME));

        RelativeLayout buttonMain = (RelativeLayout) view.findViewById(R.id.level_button_main);
        TextView textView = (TextView) view.findViewById(R.id.level_button_text);

        int res = context.getResources().getIdentifier
                ("button_level_" + levelID,
                        "drawable",
                        context.getApplicationContext().getPackageName());

        if ( res != 0) {
            Drawable level_image = context.getResources().getDrawable(res);
            level_image.setAlpha(BUTTON_ALPHA);
            buttonMain.setBackground(level_image);
        }

        textView.setText(AestheticFormat.vapour(levelNameString));

        listener(context, view, levelID, buttonMain, buttonMain, TabbedActivity.class);
    }

    private void listener(final Context context, final View view,
                          final String levelString, final View button,
                          final View buttonPress, final Class cls) {

        button.setOnTouchListener(new View.OnTouchListener() {

            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, cls);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                        View buttonInstance = view.findViewById(buttonPress.getId());
                        buttonInstance.getBackground().setAlpha(BUTTON_ALPHA_PRESS);
                        buttonInstance.invalidate();
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                        View buttonInstance = view.findViewById(buttonPress.getId());
                        bundle.putString(String.valueOf(BundleKey.LEVEL), levelString);
                        intent.putExtras(bundle);
                        buttonInstance.getBackground().setAlpha(BUTTON_ALPHA);
                        context.startActivity(intent);
                        break;
                    }

                    case MotionEvent.ACTION_CANCEL: {
                        View buttonInstance = view.findViewById(buttonPress.getId());
                        buttonInstance.getBackground().setAlpha(BUTTON_ALPHA);
                        buttonInstance.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
    }
}
