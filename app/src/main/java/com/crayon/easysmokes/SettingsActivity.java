package com.crayon.easysmokes;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crayon.easysmokes.builder.AdvancedSwitch;

import java.io.InputStream;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout container = (LinearLayout) findViewById(R.id.content_settings);

        new AdvancedSwitch("Automatic Cache",
                "Automatically cache Favourites to external storage (SD Card) if available.",
                this, container);
    }
}
