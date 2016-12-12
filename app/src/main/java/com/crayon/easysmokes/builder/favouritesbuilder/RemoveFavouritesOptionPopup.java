package com.crayon.easysmokes.builder.favouritesbuilder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.RemoveFavouritesActivity;
import com.crayon.easysmokes.data.DataBase;
import com.crayon.easysmokes.data.sqlimports.NadeData;

import java.util.List;

/**
 * Created by Jay on 12/12/2016.
 */

public class RemoveFavouritesOptionPopup {

    Context context;
    View view;
    ListView listView;
    List<String> queue;
    DataBase database;
    int favCount;

    public RemoveFavouritesOptionPopup(Context context, View view, ListView listView, List<String> queue, DataBase database, int favCount) {
        this.context = context;
        this.view = view;
        this.listView = listView;
        this.queue = queue;
        this.database = database;
        this.favCount = favCount;

    }

    public PopupWindow getPopup() {
        final View container = LayoutInflater.from(context).inflate(R.layout.popupmenu_removefavouriteoptions, null);

        final PopupWindow popupWindow = new PopupWindow(container,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        // SET up button functionality
        RelativeLayout selectAllButton = (RelativeLayout) container.findViewById(R.id.RemoveFavOptions_selectall);
        RelativeLayout deleteButton = (RelativeLayout) container.findViewById(R.id.RemoveFavOptions_remove);

        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < listView.getCount(); i++) {
                    Cursor nadeDataCursor = (Cursor) listView.getAdapter().getItem(i);
                    String nadeID = nadeDataCursor.getString(nadeDataCursor.getColumnIndex(NadeData.ATT_ID));
                    queue.add(nadeID);
                }
                ((RemoveFavouritesActivity) context).refresh(queue);
                popupWindow.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean shutdown = false;
                System.out.println("QUEUE SIZE, FAVCOUNT: " + queue.size() + ", " + favCount);
                if (queue.size() >= favCount) {
                    shutdown = true;
                }
                for (String nadeID :
                        queue) {
                    database.removeFavourite(nadeID);
                }
                popupWindow.dismiss();
                if (shutdown) {
                    ((RemoveFavouritesActivity) context).finish();
                }
                queue.clear();
                ((RemoveFavouritesActivity) context).recreate();
            }
        });

        return popupWindow;
    }
}
