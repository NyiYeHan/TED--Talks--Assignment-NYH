package com.padcmyanmar.tedtalk.network;

import com.padcmyanmar.tedtalk.network.responses.GetTedTalksResponse;
import com.padcmyanmar.tedtalk.utils.TedTalksNewsConstant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TedNewsApi {

    @FormUrlEncoded
    @POST(TedTalksNewsConstant.GET_TED_TALKS)
    Call<GetTedTalksResponse> loadTedNewsList(
       @Field(TedTalksNewsConstant.PARAM_PAGE) int page,
       @Field(TedTalksNewsConstant.PARAM_ACCESS_TOKEN)     String accessToken
    );
}
