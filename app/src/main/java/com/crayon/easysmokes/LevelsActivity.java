package com.crayon.easysmokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.crayon.easysmokes.builder.LevelButton;

public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        LinearLayout layout = (LinearLayout) findViewById(R.id.LevelsActivity_ScrollView);

        new LevelButton().buildLevels(this, layout);
    }

}
