package com.padcmyanmar.tedtalk.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TalksVo {

    @SerializedName("talk_id")
    private String tedId;

    @SerializedName("title")
    private String title;

    @SerializedName("speaker")
    private SpeakerVo speakers;

    @SerializedName("imageUrl")
    private String imgUrl;

    @SerializedName("durationInSec")
    private double duringInSec;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVo> tag;

    public String getTedId() {
        return tedId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVo getSpeakers() {
        return speakers;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public double getDuringInSec() {
        return duringInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVo> getTag() {
        return tag;
    }
}
