package com.padcmyanmar.tedtalk.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.tedtalk.events.ApiErrorEvent;
import com.padcmyanmar.tedtalk.events.SuccessGetTedTedEvent;
import com.padcmyanmar.tedtalk.network.responses.GetTedTalksResponse;
import com.padcmyanmar.tedtalk.utils.TedTalksNewsConstant;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionTedTalksNewsDataAgentImpl implements TedTalksNewsDataAgent {

    private static HttpUrlConnectionTedTalksNewsDataAgentImpl obj;

    public static HttpUrlConnectionTedTalksNewsDataAgentImpl getObjInstance() {
        if (obj == null) {
            obj = new HttpUrlConnectionTedTalksNewsDataAgentImpl();
        }
        return obj;
    }

    @Override
    public void loadTedNewsList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {
                    url = new URL(TedTalksNewsConstant.API_BASE_ROOT + TedTalksNewsConstant.GET_TED_TALKS); //1.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //2.

                    connection.setReadTimeout(15 * 1000); //4. ms

                    connection.setDoInput(true); //5.
                    connection.setDoOutput(true);

                    List<NameValuePair> params = new ArrayList<>(); //6.
                    params.add(new BasicNameValuePair(TedTalksNewsConstant.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalksNewsConstant.PARAM_PAGE, String.valueOf(page)));
                    //write the parameters from NameValuePair list into connection obj.
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    connection.connect(); //7.

                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //8.
                    stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString(); //9.
                    return responseString;

                } catch (Exception e) {
                    Log.e("", e.getMessage());

                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                GetTedTalksResponse getTedTalksResponse = gson.fromJson(s, GetTedTalksResponse.class);
                Log.d("onPostExecute", "Ted News size : " + getTedTalksResponse
                        .getTalksVos().size());

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


    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
