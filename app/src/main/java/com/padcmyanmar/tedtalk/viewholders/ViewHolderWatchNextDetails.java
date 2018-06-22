package com.padcmyanmar.tedtalk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.tedtalk.R;
import com.padcmyanmar.tedtalk.data.vo.TalksVo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderWatchNextDetails extends RecyclerView.ViewHolder {
    private TalksVo mtalksVo;
    @BindView(R.id.tv_bottom2)
    TextView tvBottom2;
    @BindView(R.id.tv_top_name2)
    TextView tvTopName2;
    @BindView(R.id.iv_icon_top)
    ImageView ivIconTop;
    @BindView(R.id.watch_next_time)
    TextView tvWatchTime;

    public ViewHolderWatchNextDetails(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setTedsWatchData(TalksVo talksVo){
        mtalksVo = talksVo;
        tvTopName2.setText(mtalksVo.getSpeakers().getName());
        Glide.with(ivIconTop.getContext())
                .load(mtalksVo.getImgUrl())
                .into(ivIconTop);
        tvWatchTime.setText(TedNewsViewHolder.secondToMinute(mtalksVo.getDuringInSec()));
        tvBottom2.setText(mtalksVo.getTitle());
    }
}
