package com.padcmyanmar.tedtalk.data.model;

import com.padcmyanmar.tedtalk.data.vo.TalksVo;
import com.padcmyanmar.tedtalk.events.SuccessGetTedTedEvent;
import com.padcmyanmar.tedtalk.network.HttpUrlConnectionTedTalksNewsDataAgentImpl;
import com.padcmyanmar.tedtalk.network.OkhttpDataAgentImpl;
import com.padcmyanmar.tedtalk.network.RetorfitDataAgentImpl;
import com.padcmyanmar.tedtalk.network.TedTalksNewsDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TedTalksNewsModel {
    private static TedTalksNewsModel obj;
    private List<TalksVo> mTalkVoList;
    private Map<String, TalksVo> mTedsMap;

    private TedTalksNewsDataAgent tedTalksNewsDataAgent;
    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TedTalksNewsModel() {
        //tedTalksNewsDataAgent = HttpUrlConnectionTedTalksNewsDataAgentImpl.getObjInstance();
        //tedTalksNewsDataAgent = OkhttpDataAgentImpl.getObjInstance();
        tedTalksNewsDataAgent = RetorfitDataAgentImpl.getObjectInstance();
        mTedsMap = new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static TedTalksNewsModel getObj() {
        if (obj == null) {
            obj = new TedTalksNewsModel();
        }
        return obj;


    }

    public TalksVo getTedTalksById(String tedId) {
        return mTedsMap.get(tedId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTedtalks(SuccessGetTedTedEvent event) {
        for (TalksVo talksVo : event.getTalksVos()) {
            mTedsMap.put(talksVo.getTedId(), talksVo);
        }
        mTalkVoList = event.getTalksVos();
    }

    public void loadTedNewsList() {
        tedTalksNewsDataAgent.loadTedNewsList(1, DUMMY_ACCESS_TOKEN);
    }

    public Map<String, TalksVo> getmTedsMap() {
        return mTedsMap;
    }

    public List<TalksVo> getmTalkVoList() {
        return mTalkVoList;
    }
}
