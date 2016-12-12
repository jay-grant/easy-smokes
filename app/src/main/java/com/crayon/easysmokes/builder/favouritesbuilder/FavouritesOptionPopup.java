package com.crayon.easysmokes.builder.favouritesbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.RemoveFavouritesActivity;
import com.crayon.easysmokes.builder.DisplayPixelScale;

import static android.view.View.GONE;

/**
 * Created by Jay on 9/12/2016.
 */

public class FavouritesOptionPopup {

    Activity context;
    View parentView;
    boolean hasFavs;

    public FavouritesOptionPopup(Context context, View parentView, boolean hasFavs) {
        this.context = (Activity) context;
        this.parentView = parentView;
        this.hasFavs = hasFavs;
    }

    /**
     * Inflates and returns a custom options menu to be used in the Favourites Activity.
     * This menu will include functionality that interacts with shared preferences to modify
     * user settings as well as DataBase to remove favourites.
     *
     * @return a View
     */
    public PopupWindow getPopup() {
        View container = LayoutInflater.from(context).inflate(R.layout.popupmenu_favouriteoptions, null);

        final PopupWindow popupWindow = new PopupWindow(container,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        final RelativeLayout orderButton = (RelativeLayout) container.findViewById(R.id.FavouriteOption_order);
        final RelativeLayout groupButton = (RelativeLayout) container.findViewById(R.id.FavouriteOption_group);
        RelativeLayout removeButton = (RelativeLayout) container.findViewById(R.id.FavouriteOption_remove);

        if (!hasFavs) {
            removeButton.setClickable(false);
            removeButton.setAlpha(0);
        } else {
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RemoveFavouritesActivity.class);
                    context.startActivity(intent);
                    popupWindow.dismiss();
                }
            });
        }

        return popupWindow;
    }
}
