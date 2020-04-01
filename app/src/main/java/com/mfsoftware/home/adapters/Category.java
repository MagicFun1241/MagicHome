package com.mfsoftware.home.adapters;

import android.content.Context;

public class Category {
    private String title;
    private int preview;

    public Category(Context context, int text, int previewImage) {
        this.title = context.getString(text);
        this.preview = previewImage;
    }

    public String getTitle() {
        return title;
    }

    public int getPreview() {
        return preview;
    }
}
