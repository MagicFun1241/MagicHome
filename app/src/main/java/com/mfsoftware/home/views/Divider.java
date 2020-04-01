package com.mfsoftware.home.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class Divider extends View {

    public Divider(Context context) {
        super(context);

        final int dp = (int) (getResources().getDisplayMetrics().density + 0.5f);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp);
        setLayoutParams(params);

        setBackgroundColor(1);
    }

    public Divider(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Divider(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
