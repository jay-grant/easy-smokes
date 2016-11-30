package com.crayon.easysmokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.crayon.easysmokes.builder.HomeScreenButton;

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
        new HomeScreenButton("M a p s", this, LevelsActivity.class, layout, R.drawable.ic_home_maps, 0);
        new HomeScreenButton("F a v o u r i t e s", this, FavouritesActivity.class, layout, R.drawable.ic_home_favourites, 0);
        new HomeScreenButton("S e t t i n g s", this, SettingsActivity.class, layout, R.drawable.ic_home_settings, 1);

    }
}
