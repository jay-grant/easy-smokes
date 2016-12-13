package com.crayon.easysmokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.crayon.easysmokes.builder.AestheticFormat;
import com.crayon.easysmokes.builder.HomeScreenButton;
import com.crayon.easysmokes.data.DataBase;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Hiding the Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

//        Defining the Layout in which the Buttons will be constructed
        LinearLayout layout = (LinearLayout) findViewById(R.id.HomeActivity_LinearLayout);

//        Constructing Buttons
        new HomeScreenButton(AestheticFormat.vapour("Maps"), this, LevelsActivity.class,
                layout, R.drawable.ic_home_maps, false);
        new HomeScreenButton(AestheticFormat.vapour("Favourites"), this, FavouritesActivity.class,
                layout, R.drawable.ic_home_favourites, false);
        new HomeScreenButton(AestheticFormat.vapour("Settings"), this, SettingsActivity.class,
                layout, R.drawable.ic_home_settings, true);
    }
}
