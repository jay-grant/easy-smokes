package com.crayon.easysmokes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crayon.easysmokes.builder.AestheticFormat;
import com.crayon.easysmokes.builder.GetNadeImageHelper;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.DemoData;
import com.crayon.easysmokes.model.BundleKey;
import com.crayon.easysmokes.model.TouchImageView;

import java.util.ArrayList;
import java.util.List;


public class SwipeFragment extends Fragment {

    TouchImageView touchImageView;
    ImageView alertImage;
    TextView alertText;

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

        String url;
        String nadeID;
        Bundle bundle = getArguments();

        nadeID = bundle.getString(String.valueOf(BundleKey.NADEID));

        DataBase database = new DataBase(this.getContext());
        List<String> titles = database.selectFromWhere(DemoData.ATT_TITLE, DemoData.TABLE_NAME, DemoData.ATT_NADE, DataBase.toSQLString(nadeID));
        List<String> descriptions = database.selectFromWhere(DemoData.ATT_DESC, DemoData.TABLE_NAME, DemoData.ATT_NADE, DataBase.toSQLString(nadeID));
        List<String> urls = database.selectFromWhere(DemoData.ATT_IMG, DemoData.TABLE_NAME, DemoData.ATT_NADE, DataBase.toSQLString(nadeID));

        // This is out index for which demo we are up to
        int pos = bundle.getInt(String.valueOf(BundleKey.FRAGMENTPOS));

        View v;
        v = inflater.inflate(R.layout.fragment_swipe,container,false);
        touchImageView =
                (TouchImageView) v.findViewById(R.id.swipeFragment_TouchImageView);
        alertImage = (ImageView) v.findViewById(R.id.imageView);
        alertText = (TextView) v.findViewById(R.id.textView);
        alertImage.setColorFilter(Color.parseColor("#ffcc33"), PorterDuff.Mode.SRC_ATOP);

        if (urls != null && !urls.isEmpty()) {
            url = urls.get(pos);
        } else {
            showAlertIcon("no images found");
            return v;
        }

        if (!SwipeLauncher.isNetworkAvailable(this.getContext())) {
            showAlertIcon("connection error");
            return v;
        }

        if (url == null || url.isEmpty() || url.equals("")) {
            showAlertIcon("invalid image");
            return v;
        }

        GetNadeImageHelper.getImages(getContext(), touchImageView, url);
        v.setBackgroundColor(getResources().getColor(R.color.black));
        return v;
    }

    private void showAlertIcon(String text) {
        alertImage.setVisibility(View.VISIBLE);
        alertText.setVisibility(View.VISIBLE);
        alertText.setText(AestheticFormat.vapour(text));
    }

}
