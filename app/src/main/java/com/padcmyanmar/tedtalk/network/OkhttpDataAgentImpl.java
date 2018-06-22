package com.padcmyanmar.tedtalk.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.tedtalk.events.ApiErrorEvent;
import com.padcmyanmar.tedtalk.events.SuccessGetTedTedEvent;
import com.padcmyanmar.tedtalk.network.responses.GetTedTalksResponse;
import com.padcmyanmar.tedtalk.utils.TedTalksNewsConstant;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpDataAgentImpl implements TedTalksNewsDataAgent {
    private static OkhttpDataAgentImpl objInstance;
    private OkHttpClient mokHttpClient;

    private OkhttpDataAgentImpl() {
        mokHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkhttpDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new OkhttpDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTedNewsList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                RequestBody formBody = new FormBody.Builder()
                        .add(TedTalksNewsConstant.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TedTalksNewsConstant.PARAM_PAGE, String.valueOf(page))
                        .build();
                Request request = new Request.Builder()
                        .url(TedTalksNewsConstant.API_BASE_ROOT + TedTalksNewsConstant.GET_TED_TALKS)
                        .post(formBody)
                        .build();

                try {
                    Response response = mokHttpClient.newCall(request).execute(); //4.
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();

                        return responseString;
                    }
                } catch (IOException e) {
                    Log.e(" ", e.getMessage());

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                GetTedTalksResponse getTedTalksResponse = gson.fromJson(s,GetTedTalksResponse.class);
                if (getTedTalksResponse.isResponseOk()) {
                    SuccessGetTedTedEvent event = new SuccessGetTedTedEvent(getTedTalksResponse.getTalksVos());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent event = new ApiErrorEvent(getTedTalksResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }
        }.execute();
    }
}
