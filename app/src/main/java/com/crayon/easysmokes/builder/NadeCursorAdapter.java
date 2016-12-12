package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.SwipeLauncher;
import com.crayon.easysmokes.animation.NotificationHelper;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.NadeData;
import com.crayon.easysmokes.model.BundleKey;

import java.security.PublicKey;

/**
 * Created by Jay on 7/12/2016.
 */

public class NadeCursorAdapter extends CursorAdapter {

    NotificationHelper notificationHelper;

    public NadeCursorAdapter(Context context, Cursor cursor, NotificationHelper notificationHelper) {
        super(context, cursor, 0);
        this.notificationHelper = notificationHelper;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.button_nade, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        RelativeLayout buttonMain = (RelativeLayout) view.findViewById(R.id.nade_button_main);
        TextView textMain = (TextView) view.findViewById(R.id.nade_button_text);
        Button buttonFav = (Button) view.findViewById(R.id.nade_button_fav);

        final String nadeID = cursor.getString(cursor.getColumnIndex(NadeData.ATT_ID));
        final String nadeName = cursor.getString(cursor.getColumnIndex(NadeData.ATT_NAME));
        final String nadeLevel = cursor.getString(cursor.getColumnIndex(NadeData.ATT_LEVEL));
        final String nadeSide = cursor.getString(cursor.getColumnIndex(NadeData.ATT_SIDE));

        // Set Favorite Icon
        DataBase database = new DataBase(context);
        setHeartIcon(buttonFav, context, database.isFavourited(nadeID));

        textMain.setText(nadeID + " : " + nadeName);

        listener(context, view, nadeID, buttonMain);
        favListener(context, view, nadeID, buttonFav, notificationHelper, (LinearLayout) view.getParent());
    }

    public static void listener(final Context context, final View view,
                                 final String nade, final View button) {

        button.setOnTouchListener(new NadeTouchListener(nade));
    }

    private void favListener(final Context context, final View view,
                             final String nadeID, final Button button,
                             final NotificationHelper notificationHelper,
                             final LinearLayout layout) {

        button.setOnTouchListener(new View.OnTouchListener() {

            DataBase database = new DataBase(context);
            int pressColour = context.getResources().getColor(R.color.colorPrimary);
            int greenNoti = context.getResources().getIdentifier
                    ("shape_notification_green", "drawable", context.getPackageName());
            int redNoti = context.getResources().getIdentifier
                    ("shape_notification_red", "drawable", context.getPackageName());

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button buttonInstance = (Button) view.findViewById(button.getId());
                        buttonInstance.getBackground()
                                .setColorFilter(pressColour, PorterDuff.Mode.SRC_ATOP);
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                        Button buttonInstance = (Button) view.findViewById(button.getId());
                        buttonInstance.getBackground().clearColorFilter();
                        if (database.isFavourited(nadeID)) {
                            database.removeFavourite(nadeID);
                            notificationHelper.getNotification("Removed from Favs", redNoti);
                            setHeartIcon(button, context, false);
                        } else {
                            database.addFavourite(nadeID);
                            notificationHelper.getNotification("Added to Favs", greenNoti);
                            setHeartIcon(button, context, true);
                        }
                        buttonInstance.invalidate();
                    }


                    case MotionEvent.ACTION_CANCEL: {
                        Button buttonInstance = (Button) view.findViewById(button.getId());
                        buttonInstance.getBackground().clearColorFilter();
                        buttonInstance.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void setHeartIcon(Button button, Context context, boolean fill) {
        if (fill) {
            button.setBackgroundResource(context.getResources()
                    .getIdentifier("ic_fav_full",
                            "drawable", context.getPackageName()));
        } else {
            button.setBackgroundResource(context.getResources()
                    .getIdentifier("ic_fav_empty",
                            "drawable", context.getPackageName()));
        }
    }
}
