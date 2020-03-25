package com.mfsoftware.home.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mfsoftware.home.R;

public class DialogActionBar extends LinearLayout {
    public DialogActionBar(Context context) {
        super(context);

        Resources resources = getResources();

        ImageButton back = new ImageButton(context);
        back.setImageDrawable(resources.getDrawable(R.drawable.ic_chevron_left, null));

        addView(back);
    }

    public DialogActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DialogActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DialogActionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
