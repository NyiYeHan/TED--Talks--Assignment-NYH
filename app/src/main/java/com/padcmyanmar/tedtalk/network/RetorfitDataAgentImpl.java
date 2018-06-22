package com.padcmyanmar.tedtalk.network;

import com.padcmyanmar.tedtalk.events.ApiErrorEvent;
import com.padcmyanmar.tedtalk.events.SuccessGetTedTedEvent;
import com.padcmyanmar.tedtalk.network.responses.GetTedTalksResponse;
import com.padcmyanmar.tedtalk.utils.TedTalksNewsConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetorfitDataAgentImpl implements TedTalksNewsDataAgent {

    private static RetorfitDataAgentImpl sreRetorfitDataAgent;

    private TedNewsApi mtedNewsApi;

    private RetorfitDataAgentImpl() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TedTalksNewsConstant.API_BASE_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mtedNewsApi = retrofit.create(TedNewsApi.class);

    }

    public static RetorfitDataAgentImpl getObjectInstance() {
        if (sreRetorfitDataAgent == null) {
            sreRetorfitDataAgent = new RetorfitDataAgentImpl();
        }
        return sreRetorfitDataAgent;

    }


    @Override
    public void loadTedNewsList(int page, String accessToken) {

        Call<GetTedTalksResponse> getTedNewsResponseCall = mtedNewsApi.loadTedNewsList(page, accessToken);
        getTedNewsResponseCall.enqueue(new Callback<GetTedTalksResponse>() {
            @Override
            public void onResponse(Call<GetTedTalksResponse> call, Response<GetTedTalksResponse> response) {
                GetTedTalksResponse tedNewsResponse = response.body();
                if (tedNewsResponse != null && tedNewsResponse.isResponseOk()) {
                    SuccessGetTedTedEvent event = new SuccessGetTedTedEvent(tedNewsResponse.getTalksVos());
                    EventBus.getDefault().post(event);
                } else {
                    if (tedNewsResponse == null) {
                        ApiErrorEvent event = new ApiErrorEvent("Empty response is ok");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(tedNewsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTedTalksResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });


    }
}
