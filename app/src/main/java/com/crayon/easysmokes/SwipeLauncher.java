package com.crayon.easysmokes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.crayon.easysmokes.data.Data;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.NadeImage;
import com.crayon.easysmokes.model.PagerAdapter;

import java.util.ArrayList;

public class SwipeLauncher extends FragmentActivity {

    ViewPager viewPager;
    ArrayList<String> urls = new ArrayList<>();
    boolean networkCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_launcher);

        Bundle bundle = getIntent().getExtras();

        NadeImage nadeImage = Data.getNadeImage(bundle.getString(String.valueOf(BundleKey.NADEID)));

        if (nadeImage != null) {
            urls = nadeImage.getUrlArray();
        } else {
            urls = null;
        }

        networkCheck = isNetworkAvailable();

        bundle.putStringArrayList(String.valueOf(BundleKey.URLLIST), urls);
        bundle.putBoolean(String.valueOf(BundleKey.NETWORK), networkCheck);

        viewPager = (ViewPager) findViewById(R.id.swipeLauncher_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), bundle);
        viewPager.setAdapter(pagerAdapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
