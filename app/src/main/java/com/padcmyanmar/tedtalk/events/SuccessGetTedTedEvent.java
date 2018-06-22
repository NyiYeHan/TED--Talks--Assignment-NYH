package com.padcmyanmar.tedtalk.events;

import com.padcmyanmar.tedtalk.data.vo.TalksVo;

import java.util.List;

public class SuccessGetTedTedEvent {
   private List<TalksVo> talksVos;

    public SuccessGetTedTedEvent(List<TalksVo> talksVos) {
        this.talksVos = talksVos;
    }

    public List<TalksVo> getTalksVos() {
        return talksVos;
    }
}
