package com.padcmyanmar.tedtalk.data.vo;

import com.google.gson.annotations.SerializedName;

public class TagVo {
    @SerializedName("tag_id")
    private String tagId;

    @SerializedName("tag")
    private String tag;

    @SerializedName("description")
    private String description;

    public String getTagId() {
        return tagId;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }
}
