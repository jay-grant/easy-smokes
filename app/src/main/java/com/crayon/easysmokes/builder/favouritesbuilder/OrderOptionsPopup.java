package com.crayon.easysmokes.builder.favouritesbuilder;

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

public class OrderOptionsPopup {

    Context context;
    View parentView;

    public OrderOptionsPopup(Context context, View parentView) {
        this.context = context;
        this.parentView = parentView;
    }

    public PopupWindow getPopup() {
        View container = LayoutInflater.from(context).inflate(R.layout.popupmenu_orderoptions, null);
        final PopupWindow popupWindow = new PopupWindow(container,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        // Setting the button text colour dependant on the user setting
        final Button newestButton = (Button) container.findViewById(R.id.orderoptions_newestbutton);
        final Button oldestButton = (Button) container.findViewById(R.id.orderoptions_oldestbutton);
        final SharedPreferences settings = context.getApplicationContext().getSharedPreferences(SharedPrefsKey.FAV_SETTINGS.toString(), Context.MODE_PRIVATE);
        String preference = settings.getString(SharedPrefsKey.FAV_ORDER.toString(), FavouriteOrderKeys.NEWEST.toString());
        resetButtonColours(newestButton, oldestButton, preference);

        newestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.edit().putString(SharedPrefsKey.FAV_ORDER.toString(), FavouriteOrderKeys.NEWEST.toString()).apply();
                resetButtonColours(newestButton, oldestButton, FavouriteOrderKeys.NEWEST.toString());
                ((FavouritesActivity) context).refresh();
            }
        });
        oldestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.edit().putString(SharedPrefsKey.FAV_ORDER.toString(), FavouriteOrderKeys.OLDEST.toString()).apply();
                resetButtonColours(newestButton, oldestButton, FavouriteOrderKeys.OLDEST.toString());
                ((FavouritesActivity) context).refresh();
            }
        });

        return popupWindow;
    }

    private void resetButtonColours(Button newestButton, Button oldestButton, String preference) {
        if (preference.equals(FavouriteOrderKeys.NEWEST.toString())) {
            newestButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
            oldestButton.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            newestButton.setTextColor(context.getResources().getColor(R.color.white));
            oldestButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }
}
