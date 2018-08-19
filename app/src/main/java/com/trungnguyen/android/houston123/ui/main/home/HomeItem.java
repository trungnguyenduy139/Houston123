package com.trungnguyen.android.houston123.ui.main.home;

/**
 * Created by trungnd4 on 19/08/2018.
 */
public class HomeItem {
    private String title;
    private int resourceId;

    public HomeItem(String title, int resourceId) {
        this.title = title;
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
