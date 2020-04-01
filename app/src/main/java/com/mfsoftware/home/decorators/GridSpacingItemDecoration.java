package com.mfsoftware.home.decorators;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public GridSpacingItemDecoration(Context context, int space) {
        final int dp = (int) (context.getResources().getDisplayMetrics().density + 0.5f);
        this.space = space * dp;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;// parent.getChildLayoutPosition(view) == 0 ? space : 0;
    }
}