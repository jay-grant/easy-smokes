package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crayon.easysmokes.R;

public class HomeScreenButton {

    public HomeScreenButton(String text, final Context context,
                            final Class cls, LinearLayout layout, int iconResId, boolean isBottomLine) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.button_home, null);
        RelativeLayout buttonMain = (RelativeLayout) view.findViewById(R.id.HomeButton_main);
        TextView textView = (TextView) view.findViewById(R.id.HomeButton_text);
        ImageView buttonLine = (ImageView) view.findViewById(R.id.HomeButton_line);
        ImageView buttonIcon = (ImageView) view.findViewById(R.id.HomeButton_icon);

        if (isBottomLine) {
            buttonLine.setVisibility(View.INVISIBLE);
        }

        buttonIcon.setBackgroundResource(iconResId);

        textView.setText(text);

        listener(context, view, buttonMain, buttonMain, cls);

        layout.addView(view);
    }


    private void listener(final Context context, final View view,
                          View button,
                          final View buttonPress, final Class cls) {

        button.setOnTouchListener(new View.OnTouchListener() {
            int grey = context.getResources().getColor(R.color.colorPrimary);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        View buttonInstance = view.findViewById(buttonPress.getId());
                        buttonInstance.getBackground()
                                .setColorFilter(grey, PorterDuff.Mode.SRC_ATOP);
                        buttonInstance.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        context.startActivity(new Intent(context, cls));

                        v.getBackground().clearColorFilter();
                    case MotionEvent.ACTION_CANCEL: {
                        View buttonInstance = view.findViewById(buttonPress.getId());
                        buttonInstance.getBackground().clearColorFilter();
                        buttonInstance.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
    }

}
