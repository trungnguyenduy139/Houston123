package com.trungnguyen.android.houston123.ui.userdetail;

/**
 * Created by trungnd4 on 14/08/2018.
 */
public class ItemDetailModel {
    private String key = "";
    private String value = "";

    public ItemDetailModel() {
    }

    public ItemDetailModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
