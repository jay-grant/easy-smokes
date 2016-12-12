package com.crayon.easysmokes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.DemoData;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.NadeImage;
import com.crayon.easysmokes.model.PagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SwipeLauncher extends FragmentActivity {

    ViewPager viewPager;
    boolean networkCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_launcher);

        Bundle bundle = getIntent().getExtras();
        String nadeID = bundle.getString(String.valueOf(BundleKey.NADEID));

        DataBase database = new DataBase(this);
        int numberDemos = database.countDemos(nadeID);

        bundle.putInt(String.valueOf(BundleKey.NUMBER_DEMOS), numberDemos);
        bundle.putString(String.valueOf(BundleKey.NADEID), nadeID);
        bundle.putBoolean(String.valueOf(BundleKey.NETWORK), isNetworkAvailable(this));

        viewPager = (ViewPager) findViewById(R.id.swipeLauncher_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), bundle);
        viewPager.setAdapter(pagerAdapter);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
