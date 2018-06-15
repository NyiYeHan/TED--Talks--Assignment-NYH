package com.padcmyanmar.tedtalk.data.model;

import com.padcmyanmar.tedtalk.network.HttpUrlConnectionTedTalksNewsDataAgentImpl;
import com.padcmyanmar.tedtalk.network.TedTalksNewsDataAgent;

public class TedTalksNewsModel {
    private static TedTalksNewsModel obj;
    private TedTalksNewsDataAgent tedTalksNewsDataAgent;
    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TedTalksNewsModel() {
        tedTalksNewsDataAgent = HttpUrlConnectionTedTalksNewsDataAgentImpl.getObjInstance();
    }

    public static TedTalksNewsModel getObj(){
        if (obj == null){
            obj = new TedTalksNewsModel();
        }
        return obj;
    }

    public  void loadTedNewsList(){
        tedTalksNewsDataAgent.loadTedNewsList(1,DUMMY_ACCESS_TOKEN);
    }
}
