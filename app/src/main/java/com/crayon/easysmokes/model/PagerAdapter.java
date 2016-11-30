package com.crayon.easysmokes.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crayon.easysmokes.SwipeFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {

    Bundle bundle;
    ArrayList<String> urls;
    int imagesLength = 1;
    boolean networkCheck;

    public PagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
        this.urls = bundle.getStringArrayList(String.valueOf(BundleKey.URLLIST));
        this.networkCheck = bundle.getBoolean(String.valueOf(BundleKey.NETWORK));

        if (urls != null && networkCheck) {
            this.imagesLength = urls.size();
        } else {
            this.imagesLength = 1;
        }
    }

    @Override
    public Fragment getItem(int i) {

        Bundle args = new Bundle();
        args.putInt(String.valueOf(BundleKey.FRAGMENTPOS), i);
        args.putStringArrayList(String.valueOf(BundleKey.URLLIST), urls);

        SwipeFragment fragment = new SwipeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return imagesLength;
    }
}
