package com.mfsoftware.home.views;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;

import com.mfsoftware.home.R;
import com.squareup.picasso.Picasso;

public class UserAppBar extends LinearLayout {
    private ProfileImageView avatar;

    public UserAppBar(Context context) {
        super(context);

        final float factor = context.getResources().getDisplayMetrics().density;

        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setOrientation(VERTICAL);

        int padding = (int)(12 * factor);
        setPadding(padding, padding, padding, padding);

        LayoutParams layoutParams = new LayoutParams((int)(36 * factor), (int)(36 * factor));
        layoutParams.gravity = Gravity.END;

        avatar = new ProfileImageView(getContext());
        avatar.setLayoutParams(layoutParams);
        avatar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));

        addView(avatar);
    }

    public void setUserAvatar(String url) {
        Picasso.get().load(url).into(avatar);
    }
}
