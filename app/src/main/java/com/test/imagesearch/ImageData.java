package com.test.imagesearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ImageData {

    @SerializedName("pages")
    @Expose
    private Map<String, Data> pages;

    public Map<String, Data> getPages() {
        return pages;
    }

    public void setPages(Map<String, Data> pages) {
        this.pages = pages;
    }
}
