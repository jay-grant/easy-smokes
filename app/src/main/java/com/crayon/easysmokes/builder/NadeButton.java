package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.SwipeLauncher;
import com.crayon.easysmokes.animation.NotificationHelper;
import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.Nade;
import com.crayon.easysmokes.model.SharedKey;
import com.crayon.easysmokes.model.SideType;

public class NadeButton {

    String level;
    String side;

    public NadeButton(String level, String side, final Context context,
                      LinearLayout layout, NotificationHelper notificationHelper) {

        layout.removeAllViews();
        SharedPreferences sharedPrefs = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedKey.FAVS), Context.MODE_PRIVATE);

        this.level = level;
        this.side = side;

        for (final Nade nade:
                Data.getNadeList()) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.button_nade, null);
            Button buttonMain = (Button) view.findViewById(R.id.nade_button_main);
            Button buttonFav = (Button) view.findViewById(R.id.nade_button_fav);

            String nadeLevel = nade.getLevelName().toString();
            String nadeSide = nade.getSide().toString();

            checkFav(context, sharedPrefs, nade, buttonFav);

            if (level.equals(nadeLevel) &&
                    (side.equals(nadeSide) || SideType.U.toString().equals(nadeSide)))
            {
                buttonMain.setText(nade.getName() + " : " + nade.getId());
                listener(context, view, nade, buttonMain);
                favListener(context, view, nade, buttonFav, notificationHelper, layout);
                layout.addView(view);
            }

        }

    }

    private void checkFav(Context context, SharedPreferences sharedPrefs, Nade nade, Button buttonFav) {
        if (sharedPrefs.contains(nade.getId())) {
            buttonFav.setBackgroundResource(context.getResources()
                    .getIdentifier("ic_fav_full",
                            "drawable", context.getPackageName()));
        } else {
            buttonFav.setBackgroundResource(context.getResources()
                    .getIdentifier("ic_fav_empty",
                            "drawable", context.getPackageName()));
        }
    }

    private static void listener(final Context context, final View view,
                                 final Nade nade, final Button button) {

        button.setOnTouchListener(new View.OnTouchListener() {

            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, SwipeLauncher.class);
            int grey = context.getResources().getColor(R.color.colorPrimary);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button buttonInstance = (Button) view.findViewById(button.getId());
                        buttonInstance.getBackground()
                                .setColorFilter(grey, PorterDuff.Mode.SRC_ATOP);
                        buttonInstance.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        bundle.putString(String.valueOf(BundleKey.NADEID), nade.getId());
                        intent.putExtras(bundle);
                        context.startActivity(intent);

                        v.getBackground().clearColorFilter();
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

    private void favListener(final Context context, final View view,
                                    final Nade nade, final Button button,
                                    final NotificationHelper notificationHelper,
                                    final LinearLayout layout) {

        button.setOnTouchListener(new View.OnTouchListener() {

            int grey = context.getResources().getColor(R.color.colorPrimary);
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(String.valueOf(SharedKey.FAVS), Context.MODE_PRIVATE);
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
                                .setColorFilter(grey, PorterDuff.Mode.SRC_ATOP);
                        buttonInstance.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (sharedPrefs.contains(nade.getId())) {
                            nade.removeFav(context);
                            notificationHelper.getNotification("Removed from Favs", redNoti);
                        } else {
                            nade.addFav(context);
                            notificationHelper.getNotification("Added to Favs", greenNoti);
                        }

                        new NadeButton(level, side, context, layout, notificationHelper);

                        v.getBackground().clearColorFilter();
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
}


