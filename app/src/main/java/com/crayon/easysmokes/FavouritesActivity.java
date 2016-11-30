package com.crayon.easysmokes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crayon.easysmokes.builder.LevelButton;
import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.model.Nade;
import com.crayon.easysmokes.model.SharedKey;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        final SharedPreferences sharedPreferences = this.getApplicationContext()
                .getSharedPreferences(String.valueOf(SharedKey.FAVS), Context.MODE_PRIVATE);


        final LinearLayout layout = (LinearLayout) findViewById(R.id.favouritesActivity_ScrollView);
        final Context context = this;
        Button button = (Button) findViewById(R.id.favourites_addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LevelsActivity.class));
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAllDialog(context, sharedPreferences);
            }
        });

        if (isFavEmpty(sharedPreferences)) {
            fab.setVisibility(View.GONE);
        } else {
            new LevelButton().buildFavourites(this, layout);
            button.setVisibility(View.GONE);
        }


    }

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
                //TODO
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isFavEmpty(SharedPreferences sharedPrefs) {
        int i = 0;
        for (Nade nade :
                Data.getNadeList()) {
            String nadeID = nade.getId();
            if (sharedPrefs.contains(nadeID)) {
                i += 1;
            }
        }
        return !(i>0);
    }

}
