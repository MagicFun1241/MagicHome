package com.mfsoftware.home.adapters;

class Category {
    private String title;
    private int preview;

    public Category(String title, int previewImage) {
        this.title = title;
        this.preview = previewImage;
    }

    public String getTitle() {
        return title;
    }

    public int getPreview() {
        return preview;
    }
}
