package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crayon.easysmokes.FavouritesActivity;
import com.crayon.easysmokes.R;
import com.crayon.easysmokes.SwipeLauncher;
import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.Nade;
import com.crayon.easysmokes.model.SharedPrefsKey;
import com.crayon.easysmokes.model.SideType;

import java.util.Map;

/**
 * Created by Jay on 2/12/2016.
 */

public class FavNadeButton {

    public void buildFavourites(final Context context, LinearLayout layout) {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, new DisplayPixelScale().getDP(90, context)
        );

        SharedPreferences sharedPrefs = context.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedPrefsKey.FAVS), Context.MODE_PRIVATE);

        Map<String,?> keys = sharedPrefs.getAll();
        Map<String, Nade> nadeMap = Data.getNadesMap();

        for(Map.Entry<String,?> entry :
                keys.entrySet()){

            if (!nadeMap.containsKey(entry.getKey())) {
                break;
            }
            Nade nade = nadeMap.get(entry.getKey());

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.button_favourite, null);
            Button buttonMain = (Button) view.findViewById(R.id.favnade_button_main);
            Button buttonDelete = (Button) view.findViewById(R.id.favnade_button_delete);
            Button buttonLine = (Button) view.findViewById(R.id.favnade_button_line);

            String nadeLevel = nade.getLevelName().toString();
            String nadeSide = nade.getSide().toString();

            listener(context, view, nade, buttonMain);
            listener(context, view, nade, buttonLine);
            removeListener(context, view, nade, buttonDelete);

            if (nade.getSide() == SideType.CT) {
                buttonLine.setBackgroundResource(R.drawable.shape_gradient_fade_ct);
            } else if (nade.getSide() == SideType.T) {
                buttonLine.setBackgroundResource(R.drawable.shape_gradient_fade_t);
            }
            // TODO Programmatic ordering/grouping

            buttonMain.setText(nade.getName() + " : " + nade.getId());
            layout.addView(view);
        }
    }

    private static void listener(final Context context, final View view,
                                 final Nade nade, final Button button) {

        button.setOnTouchListener(new View.OnTouchListener() {

            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, SwipeLauncher.class);
            int filterColour = context.getResources().getColor(R.color.colorPrimaryLight);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button buttonInstance = (Button) view.findViewById(button.getId());
                        buttonInstance.getBackground()
                                .setColorFilter(filterColour, PorterDuff.Mode.SRC_ATOP);
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

    private static void removeListener(final Context context, View view, final Nade nade, final Button button) {

        button.setOnTouchListener(new View.OnTouchListener() {

            int filterColour = context.getResources().getColor(R.color.notiRed);
            final SharedPreferences sharedPreferences = context.getApplicationContext()
                    .getSharedPreferences(String.valueOf(SharedPrefsKey.FAVS), Context.MODE_PRIVATE);

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button.getBackground().setColorFilter(filterColour, PorterDuff.Mode.SRC_ATOP);
                        button.invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        // TODO Remove Favourite Dialog Popup
                        FavouritesActivity.removeFavouriteDialog(context, sharedPreferences, nade);
                        button.getBackground().clearColorFilter();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        button.getBackground().clearColorFilter();
                        button.invalidate();
                        break;
                }
                return true;
            }
        });
    }
}
