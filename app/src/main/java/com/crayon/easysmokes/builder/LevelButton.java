package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.SwipeLauncher;
import com.crayon.easysmokes.TabbedActivity;
import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.LevelName;
import com.crayon.easysmokes.model.Nade;
import com.crayon.easysmokes.model.SharedKey;

public class LevelButton {

    private final int BUTTON_ALPHA = 85;
    LevelName[] levels = LevelName.values();

    public void buildLevels(final Context context, LinearLayout layout) {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, new DisplayPixelScale().getDP(90, context)
        );

        for (LevelName level :
                levels) {
            final String levelNameString = level.toString();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.button_level, null);
            Button buttonMain = (Button) view.findViewById(R.id.level_button_main);
            Button buttonLine = (Button) view.findViewById(R.id.level_button_line);

            int res = context.getResources().getIdentifier
                    ("button_level_" + level.getNickName().toLowerCase(),
                            "drawable",
                            context.getApplicationContext().getPackageName());

            if ( res != 0) {
                Drawable level_image = context.getResources().getDrawable(res);
                level_image.setAlpha(BUTTON_ALPHA);
                buttonMain.setBackground(level_image);
            }

            buttonMain.setText(levelNameString);

            listener(context, view, levelNameString, buttonMain, buttonMain, TabbedActivity.class);
            listener(context, view, levelNameString, buttonLine, buttonMain, TabbedActivity.class);

            layout.addView(view);
        }
    }

    public void buildFavourites(final Context context, LinearLayout layout) {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, new DisplayPixelScale().getDP(90, context)
        );

        for (final Nade nade:
                Data.getNadeList()) {

            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(String.valueOf(SharedKey.FAVS), Context.MODE_PRIVATE);

            if (sharedPrefs.contains(nade.getId()))
            {
                Button nadeButton = new Button(context);
                nadeButton.setText(nade.getId());
                nadeButton.setLayoutParams(layoutParams);

                nadeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SwipeLauncher.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(String.valueOf(BundleKey.NADEID), nade.getId());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });

                layout.addView(nadeButton);
            }
        }
    }

    private  void listener(final Context context, final View view,
                           final String levelString, final Button button,
                           final Button buttonPress, final Class cls) {

        button.setOnTouchListener(new View.OnTouchListener() {

            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, cls);
            int grey = context.getResources().getColor(R.color.colorPrimary);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button buttonInstance = (Button) view.findViewById(buttonPress.getId());
                        buttonInstance.getBackground().setAlpha(225);
                        buttonInstance.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        bundle.putString(String.valueOf(BundleKey.LEVEL), levelString);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                        v.getBackground().clearColorFilter();
                    case MotionEvent.ACTION_CANCEL: {
                        Button buttonInstance = (Button) view.findViewById(buttonPress.getId());
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
