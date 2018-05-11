package com.test.imagesearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QueryData {

    @SerializedName("query")
    @Expose
    ImageData query;

    public ImageData getQuery() {
        return query;
    }

    public void setQuery(ImageData query) {
        this.query = query;
    }
}
