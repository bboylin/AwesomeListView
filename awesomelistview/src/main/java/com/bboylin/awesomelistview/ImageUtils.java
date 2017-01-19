package com.bboylin.awesomelistview;

import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by bboylin on 2017/1/18.
 */

public class ImageUtils {
    public static  void startAnimation(ImageView imageView) {
        int pivotType = RELATIVE_TO_SELF;
        float pivotX = .5f;
        float pivotY = .5f;
        float fromDegrees = 0f;
        float toDegrees = 359f;
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotType, pivotX, pivotType, pivotY);
        animation.setRepeatCount(INFINITE);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        imageView.startAnimation(animation);
    }
}
