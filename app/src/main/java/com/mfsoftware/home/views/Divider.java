package com.mfsoftware.home.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Divider extends View {
    public Divider(Context context) {
        super(context);

        final int dp = (int) (getResources().getDisplayMetrics().density + 0.5f);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp);
        setLayoutParams(params);

        setBackgroundColor(1);
    }
}
