package com.padcmyanmar.tedtalk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.padcmyanmar.tedtalk.R;
import com.padcmyanmar.tedtalk.adapters.AdapterTedTalksDetails;


public class NewsDetailActivityTedTalk extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_ted_talk);

        RecyclerView recyclerView = findViewById(R.id.rv_detail);
        AdapterTedTalksDetails adapterTedTalksDetails = new AdapterTedTalksDetails();

        recyclerView.setAdapter(adapterTedTalksDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


    }
}
