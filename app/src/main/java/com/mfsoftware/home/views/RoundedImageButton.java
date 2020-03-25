package com.mfsoftware.home.views;

import android.content.Context;
import android.util.AttributeSet;

public class RoundedImageButton extends androidx.appcompat.widget.AppCompatImageButton {
    public RoundedImageButton(Context context) {
        super(context);

        // this.setBackground(getDrawable(R.drawable.rounded_corners_7dp));
    }

    public RoundedImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
