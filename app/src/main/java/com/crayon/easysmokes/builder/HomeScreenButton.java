package com.crayon.easysmokes.builder;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crayon.easysmokes.R;

public class HomeScreenButton {

    public HomeScreenButton(String text, final Context context,
                            final Class cls, LinearLayout layout, int iconResId, int positionCase) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.button_home, null);
        Button buttonMain = (Button) view.findViewById(R.id.home_button_main);
        Button buttonIcon = (Button) view.findViewById(R.id.home_button_icon);
        Button buttonLine = (Button) view.findViewById(R.id.home_button_line);

        switch (positionCase) {
            case 0: break;
            case 1:
                buttonLine.setVisibility(View.INVISIBLE);
        }

        buttonIcon.setBackgroundResource(iconResId);

        buttonMain.setText(text);

        listener(context, view, buttonMain, buttonMain, cls);
        listener(context, view, buttonIcon, buttonMain, cls);
        listener(context, view, buttonLine, buttonMain, cls);

        layout.addView(view);
    }


    private void listener(final Context context, final View view,
                          Button button,
                          final Button buttonPress, final Class cls) {

        button.setOnTouchListener(new View.OnTouchListener() {
            int grey = context.getResources().getColor(R.color.colorPrimary);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button buttonInstance = (Button) view.findViewById(buttonPress.getId());
                        buttonInstance.getBackground()
                                .setColorFilter(grey, PorterDuff.Mode.SRC_ATOP);
                        buttonInstance.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        context.startActivity(new Intent(context, cls));

                        v.getBackground().clearColorFilter();
                    case MotionEvent.ACTION_CANCEL: {
                        Button buttonInstance = (Button) view.findViewById(buttonPress.getId());
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
