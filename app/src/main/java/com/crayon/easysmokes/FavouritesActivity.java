package com.crayon.easysmokes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.crayon.easysmokes.builder.DisplayPixelScale;
import com.crayon.easysmokes.builder.FavCursorAdapter;
import com.crayon.easysmokes.builder.favouritesbuilder.FavouritesOptionPopup;
import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.model.Nade;

import static android.view.View.GONE;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

//        SharedPreferences sharedPreferences = this.getApplicationContext()
//                .getSharedPreferences(String.valueOf(SharedPrefsKey.FAV_SETTINGS), Context.MODE_PRIVATE);
        ListView listView = (ListView) findViewById(R.id.FavouritesActivity_ListView);
        DataBase database = new DataBase(this);
        Cursor cursor = database.getFavourites();
        FavCursorAdapter adapter = new FavCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        final boolean hasFavs = cursor.getCount() > 0;

        final Context context = this;
        RelativeLayout button = (RelativeLayout) findViewById(R.id.favourites_addButton);
        if (!hasFavs) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, LevelsActivity.class));
                    finish();
                }
            });
        } else {
            button.setVisibility(GONE);
        }

        // TODO Convert it into a settings menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view, hasFavs);
            }
        });
    }

    public void showPopup(View v, boolean hasFavs) {
        PopupWindow popupWindow = new FavouritesOptionPopup(this, v, hasFavs).getPopup();

        int xoff = DisplayPixelScale.getDP(-170, this);
        int yoff = DisplayPixelScale.getDP(-170, this);
        popupWindow.showAsDropDown(v, xoff, yoff);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void removeAllDialog(final Context context, final SharedPreferences sharedPreferences) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_remove_all_favs, null);
        builder.setCustomTitle(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                sharedPreferences.edit().clear().apply();

                finish();
                startActivity(new Intent(context, FavouritesActivity.class));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void removeFavouriteDialog(final Context context,
                                             final SharedPreferences sharedPreferences, final Nade nade) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_remove_single_fav, null);
        builder.setCustomTitle(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Activity owner = (Activity) context;
                owner.finish();
                sharedPreferences.edit().remove(nade.getId()).apply();
                owner.startActivity(new Intent(context, FavouritesActivity.class));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
