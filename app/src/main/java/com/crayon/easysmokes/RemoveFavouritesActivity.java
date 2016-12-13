package com.crayon.easysmokes;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.crayon.easysmokes.builder.DisplayPixelScale;
import com.crayon.easysmokes.builder.favouritesbuilder.RemoveFavCursorAdapter;
import com.crayon.easysmokes.builder.favouritesbuilder.RemoveFavouritesOptionPopup;
import com.crayon.easysmokes.data.DataBase;

import java.util.ArrayList;
import java.util.List;

public class RemoveFavouritesActivity extends AppCompatActivity {

    List<String> queue = new ArrayList<>();

    public void refresh(List<String> queue) {
        this.queue = queue;
        final ListView listView = (ListView) findViewById(R.id.remove_favourites_listview);
        final DataBase dataBase = new DataBase(this);
        final Cursor cursor = dataBase.getFavourites(this);
        final int count = cursor.getCount();
        RemoveFavCursorAdapter adapter = new RemoveFavCursorAdapter(this, cursor, queue);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_favourites);

        final ListView listView = (ListView) findViewById(R.id.remove_favourites_listview);
        final DataBase dataBase = new DataBase(this);
        final Cursor cursor = dataBase.getFavourites(this);
        final int count = cursor.getCount();

        RemoveFavCursorAdapter adapter = new RemoveFavCursorAdapter(this, cursor, queue);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopup(view, listView, dataBase, count);

                //TODO change this to a popup menu with 'remove' and 'select all' options. Ensure 'remove' produces a dialog
//                for (String nadeID :
//                        queue) {
//                    dataBase.removeFavourite(nadeID);
//                }
//                if (queue.size() >= count+1) {
//                    finish();
//                }
//                recreate();
            }
        });
    }

    private void showPopup(View view, ListView listView, DataBase database, int favCount) {
        PopupWindow popupWindow = new RemoveFavouritesOptionPopup(this, view, listView, queue, database, favCount).getPopup();

        int xoff = DisplayPixelScale.getDP(-170, this);
        int yoff = DisplayPixelScale.getDP(-120, this);
        popupWindow.showAsDropDown(view, xoff, yoff);
    }

}
