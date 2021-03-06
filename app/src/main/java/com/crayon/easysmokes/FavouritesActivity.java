package com.crayon.easysmokes;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.crayon.easysmokes.builder.DisplayPixelScale;
import com.crayon.easysmokes.builder.favouritesbuilder.FavCursorAdapter;
import com.crayon.easysmokes.builder.favouritesbuilder.FavouritesOptionPopup;
import com.crayon.easysmokes.builder.favouritesbuilder.GroupOptionsPopup;
import com.crayon.easysmokes.builder.favouritesbuilder.OrderOptionsPopup;
import com.crayon.easysmokes.data.DataBase;

import static android.view.View.GONE;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    public void refresh() {
        ListView listView = (ListView) findViewById(R.id.FavouritesActivity_ListView);
        DataBase database = new DataBase(this);
        Cursor cursor = database.getFavourites(this);
        FavCursorAdapter adapter = new FavCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

//        SharedPreferences sharedPreferences = this.getApplicationContext()
//                .getSharedPreferences(String.valueOf(SharedPrefsKey.FAV_SETTINGS), Context.MODE_PRIVATE);
        ListView listView = (ListView) findViewById(R.id.FavouritesActivity_ListView);
        DataBase database = new DataBase(this);
        Cursor cursor = database.getFavourites(this);
        FavCursorAdapter adapter = new FavCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        final boolean hasFavs = cursor.getCount() > 0;

        final Context context = this;
        RelativeLayout button = (RelativeLayout) findViewById(R.id.favourites_addButton);
        if (!hasFavs) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, LevelsActivity.class));
                    finish();
                }
            });
        } else {
            button.setVisibility(GONE);
        }

        // TODO Convert it into a settings menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view, hasFavs);
            }
        });
    }

    public void showPopup(final View v, boolean hasFavs) {
        final PopupWindow popupWindow = new FavouritesOptionPopup(this, v, hasFavs).getPopup();

        final RelativeLayout orderButton = (RelativeLayout) popupWindow.getContentView().findViewById(R.id.FavouriteOption_order);
        final RelativeLayout groupButton = (RelativeLayout) popupWindow.getContentView().findViewById(R.id.FavouriteOption_group);
        final ImageView orderIcon = (ImageView) popupWindow.getContentView().findViewById(R.id.FavouriteOption_ordericon);
        final ImageView groupIcon = (ImageView) popupWindow.getContentView().findViewById(R.id.FavouriteOption_groupicon);
        int xoff = DisplayPixelScale.getDP(-170, this);
        int yoff = DisplayPixelScale.getDP(-170, this);
        popupWindow.showAsDropDown(v, xoff, yoff);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                orderPopup(v, orderIcon);
            }
        });
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                groupPopup(v, groupIcon);
            }
        });
    }

    private void orderPopup(View view, final ImageView icon) {
        PopupWindow popupWindow = new OrderOptionsPopup(this, view).getPopup();
        icon.setImageResource(R.drawable.ic_expanded_white);
        int xoff = DisplayPixelScale.getDP(-190, this);
        int yoff = DisplayPixelScale.getDP(-220, this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                icon.setImageResource(R.drawable.ic_expand_more_white);
            }
        });
        popupWindow.showAsDropDown(view, xoff, yoff);
    }

    private void groupPopup(View view, final ImageView icon) {
        PopupWindow popupWindow = new GroupOptionsPopup(this, view).getPopup();
        icon.setImageResource(R.drawable.ic_expanded_white);
        int xoff = DisplayPixelScale.getDP(-190, this);
        int yoff = DisplayPixelScale.getDP(-220, this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                icon.setImageResource(R.drawable.ic_expand_more_white);
            }
        });
        popupWindow.showAsDropDown(view, xoff, yoff);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
}
