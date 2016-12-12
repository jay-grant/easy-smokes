package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.crayon.easysmokes.R;
import com.crayon.easysmokes.SwipeLauncher;
import com.crayon.easysmokes.model.BundleKey;

/**
 * Created by Jay on 10/12/2016.
 */

public class NadeTouchListener implements View.OnTouchListener {

    String nadeID;

    public NadeTouchListener(String nadeID) {
        this.nadeID = nadeID;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        Context context = view.getContext();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, SwipeLauncher.class);
        int grey = context.getResources().getColor(R.color.colorPrimaryLight);

            switch (motionEvent.getAction()) {

                case MotionEvent.ACTION_DOWN: {
                    View buttonInstance = view.findViewById(view.getId());
                    buttonInstance.getBackground()
                            .setColorFilter(grey, PorterDuff.Mode.SRC_ATOP);
                    buttonInstance.invalidate();
                    break;
                }

                case MotionEvent.ACTION_UP:
                    bundle.putString(String.valueOf(BundleKey.NADEID), nadeID);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                    view.getBackground().clearColorFilter();

                case MotionEvent.ACTION_CANCEL: {
                    View buttonInstance = view.findViewById(view.getId());
                    buttonInstance.getBackground().clearColorFilter();
                    buttonInstance.invalidate();
                    break;
                }
            }
        return true;
    }
}
