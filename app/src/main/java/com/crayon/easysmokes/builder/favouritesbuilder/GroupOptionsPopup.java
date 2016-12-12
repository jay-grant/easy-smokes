package com.crayon.easysmokes.builder.favouritesbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.crayon.easysmokes.R;

/**
 * Created by Jay on 12/12/2016.
 */

public class GroupOptionsPopup {

    Context context;
    View parentView;

    public GroupOptionsPopup(Context context, View parentView) {
        this.context = context;
        this.parentView = parentView;
    }

    public PopupWindow getPopup() {
        View container = LayoutInflater.from(context).inflate(R.layout.popupmenu_groupingoptions, null);
        PopupWindow popupWindow = new PopupWindow(container,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        return popupWindow;
    }
}
