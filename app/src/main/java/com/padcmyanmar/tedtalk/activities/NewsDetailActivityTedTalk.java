package com.padcmyanmar.tedtalk.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.tedtalk.R;
import com.padcmyanmar.tedtalk.adapters.AdapterTedTalksDetails;
import com.padcmyanmar.tedtalk.data.model.TedTalksNewsModel;
import com.padcmyanmar.tedtalk.data.vo.TalksVo;
import com.padcmyanmar.tedtalk.events.SuccessGetTedTedEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class NewsDetailActivityTedTalk extends BaseActivity {
    @BindView(R.id.iv_details_top_image)
    ImageView ivDetailsImg;
    @BindView(R.id.tv_speaker_name)
    TextView tvSpeakerName;
    @BindView(R.id.tv_details_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_top_name)
    TextView tvTopName;
    @BindView(R.id.cimv_messi)
    CircleImageView circleImageView;
    AdapterTedTalksDetails adapterTedTalksDetails;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_ted_talk);
        ButterKnife.bind(this, this);

        RecyclerView recyclerView = findViewById(R.id.rv_detail);
        adapterTedTalksDetails = new AdapterTedTalksDetails();

        recyclerView.setAdapter(adapterTedTalksDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        String talksId = getIntent().getStringExtra("talk_id");
        TalksVo talksVo = TedTalksNewsModel.getObj().getTedTalksById(talksId);
        adapterTedTalksDetails.setMtalksVos(TedTalksNewsModel.getObj().getmTalkVoList(),talksId);
        bind(talksVo);


    }

    public void bind(TalksVo talksVo) {
        Glide.with(ivDetailsImg.getContext())
                .load(talksVo.getImgUrl())
                .into(ivDetailsImg);
        tvSpeakerName.setText(talksVo.getSpeakers().getName());
        tvDetailTitle.setText(talksVo.getTitle());
        tvDescription.setText(talksVo.getDescription());
        tvTopName.setText(talksVo.getSpeakers().getName());
        Glide.with(circleImageView.getContext())
                .load(talksVo.getImgUrl())
                .into(circleImageView);

    }

}
