package com.crayon.easysmokes.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crayon.easysmokes.SwipeFragment;
import com.crayon.easysmokes.data.DataBase;

public class PagerAdapter extends FragmentPagerAdapter {

    Bundle bundle;
    String nadeID;
    int numberFragments;
    boolean networkCheck;

    public PagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
        this.nadeID = bundle.getString(String.valueOf(BundleKey.NADEID));
        this.numberFragments = bundle.getInt(String.valueOf(BundleKey.NUMBER_DEMOS));
        this.networkCheck = bundle.getBoolean(String.valueOf(BundleKey.NETWORK));

        if (numberFragments == 0) {
            numberFragments++;
        }
    }

    @Override
    public Fragment getItem(int i) {

        Bundle bundle = new Bundle();
        bundle.putInt(String.valueOf(BundleKey.FRAGMENTPOS), i);
        bundle.putString(String.valueOf(BundleKey.NADEID), nadeID);

        SwipeFragment fragment = new SwipeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return numberFragments;
    }
}
