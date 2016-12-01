package com.crayon.easysmokes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crayon.easysmokes.builder.AestheticFormat;
import com.crayon.easysmokes.builder.GetNadeImageHelper;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.TouchImageView;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.bitmap.IonBitmapCache;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;



public class SwipeFragment extends Fragment {

    TouchImageView touchImageView;
    ImageView imageView;
    TextView textView;

    ViewGroup.LayoutParams layoutParamsMatchParent
            = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);

    ViewGroup.LayoutParams layoutParamsWrapContent
            = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String url = null;
        Bundle bundle = getArguments();
        ArrayList<String> urls = bundle.getStringArrayList(String.valueOf(BundleKey.URLLIST));
        int pos = bundle.getInt(String.valueOf(BundleKey.FRAGMENTPOS));

        if (urls != null) {
            url = urls.get(pos);
        }

        View v;
        v = inflater.inflate(R.layout.fragment_swipe,container,false);
        touchImageView =
                (TouchImageView) v.findViewById(R.id.swipeFragment_TouchImageView);
        imageView = (ImageView) v.findViewById(R.id.imageView);
        textView = (TextView) v.findViewById(R.id.textView);

//        Occupy TouchImageView ***************************************************************
        if (!isNetworkAvailable()) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText(AestheticFormat.vapour("Connection Error"));
        } else if (url == null) {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText(AestheticFormat.vapour("Invalid Image"));
        } else {
            GetNadeImageHelper.getImages(getContext(), touchImageView, url);
            v.setBackgroundColor(getResources().getColor(R.color.black));
        }

//        Return Fragment View
        return v;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
