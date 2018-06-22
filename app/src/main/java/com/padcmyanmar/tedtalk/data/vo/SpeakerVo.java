package com.padcmyanmar.tedtalk.data.vo;

import com.google.gson.annotations.SerializedName;

public class SpeakerVo {
    @SerializedName("speaker_id")
    private String speakerId;

    @SerializedName("name")
    private String name;

    public String getSpeakerId() {
        return speakerId;
    }

    public String getName() {
        return name;
    }
}
