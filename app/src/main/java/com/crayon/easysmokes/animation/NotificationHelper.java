package com.crayon.easysmokes.animation;


import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class NotificationHelper {

    TextView textView;
    Animation fadeIn;
    Animation fadeOut;

    public NotificationHelper(TextView textView) {

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(1000);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1500);
        fadeOut.setDuration(1000);

        this.textView = textView;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
    }

    public void getNotification(String message, int background) {
        textView.clearAnimation();
        textView.setVisibility(View.GONE);
        textView.setText(message);
        textView.setBackgroundResource(background);
        textView.startAnimation(fadeIn);
        textView.setVisibility(View.VISIBLE);
        textView.postDelayed(new Runnable() {
            public void run() {
                textView.startAnimation(fadeOut);
                textView.setVisibility(View.GONE);
            }
        }, 100);
    }
}
