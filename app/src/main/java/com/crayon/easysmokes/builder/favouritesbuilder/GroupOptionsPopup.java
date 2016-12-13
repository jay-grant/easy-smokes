package com.crayon.easysmokes.builder.favouritesbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.crayon.easysmokes.FavouritesActivity;
import com.crayon.easysmokes.R;
import com.crayon.easysmokes.data.FavouriteGroupKeys;
import com.crayon.easysmokes.data.FavouriteOrderKeys;
import com.crayon.easysmokes.data.SharedPrefsKey;

/**
 * Created by Jay on 12/12/2016.
 */

public class GroupOptionsPopup {

    Context context;
    View parentView;

    public GroupOptionsPopup(Context context, View parentView) {
        this.context = context;
        this.parentView = parentView;
    }

    public PopupWindow getPopup() {
        final View container = LayoutInflater.from(context).inflate(R.layout.popupmenu_groupingoptions, null);
        PopupWindow popupWindow = new PopupWindow(container,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        // Setting the button text colour dependant on the user setting
        final Button noneButton = (Button) container.findViewById(R.id.groupoptions_none_button);
        final Button levelButton = (Button) container.findViewById(R.id.groupoptions_level_button);
        final Button sideButton = (Button) container.findViewById(R.id.groupoptions_side_button);
        final SharedPreferences settings = context.getApplicationContext().getSharedPreferences(SharedPrefsKey.FAV_SETTINGS.toString(), Context.MODE_PRIVATE);
        final String preference = settings.getString(SharedPrefsKey.FAV_GROUP.toString(), FavouriteGroupKeys.NONE.toString());
        resetButtonColours(noneButton, levelButton, sideButton, preference);

        // Setting the buttons to update the shared prefs to their respective setting
        noneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.edit().putString(SharedPrefsKey.FAV_GROUP.toString(), FavouriteGroupKeys.NONE.toString()).apply();
                resetButtonColours(noneButton, levelButton, sideButton, FavouriteGroupKeys.NONE.toString());
                ((FavouritesActivity) context).refresh();
            }
        });
        levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.edit().putString(SharedPrefsKey.FAV_GROUP.toString(), FavouriteGroupKeys.LEVEL.toString()).apply();
                resetButtonColours(noneButton, levelButton, sideButton, FavouriteGroupKeys.LEVEL.toString());
                ((FavouritesActivity) context).refresh();
        }
        });
        sideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.edit().putString(SharedPrefsKey.FAV_GROUP.toString(), FavouriteGroupKeys.SIDE.toString()).apply();
                resetButtonColours(noneButton, levelButton, sideButton, FavouriteGroupKeys.SIDE.toString());
                ((FavouritesActivity) context).refresh();
            }
        });

        return popupWindow;
    }

    private void resetButtonColours(Button noneButton, Button levelButton, Button sideButton, String preference) {
        if (preference.equals(FavouriteGroupKeys.NONE.toString())) {
            noneButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
            levelButton.setTextColor(context.getResources().getColor(R.color.white));
            sideButton.setTextColor(context.getResources().getColor(R.color.white));
        }
        else if (preference.equals(FavouriteGroupKeys.LEVEL.toString())){
            noneButton.setTextColor(context.getResources().getColor(R.color.white));
            levelButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
            sideButton.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            noneButton.setTextColor(context.getResources().getColor(R.color.white));
            levelButton.setTextColor(context.getResources().getColor(R.color.white));
            sideButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }
}
