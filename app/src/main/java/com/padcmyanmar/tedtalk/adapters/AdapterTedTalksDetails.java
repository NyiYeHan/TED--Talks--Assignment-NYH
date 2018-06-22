package com.padcmyanmar.tedtalk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.tedtalk.R;

import com.padcmyanmar.tedtalk.data.vo.TalksVo;
import com.padcmyanmar.tedtalk.viewholders.ViewHolderWatchNextDetails;

import java.util.ArrayList;
import java.util.List;

public class AdapterTedTalksDetails extends RecyclerView.Adapter<ViewHolderWatchNextDetails> {
    private List<TalksVo> mtalksVos;
    int mtedId;

    public AdapterTedTalksDetails() {
        mtalksVos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderWatchNextDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_watch_next_details, parent, false);
        return new ViewHolderWatchNextDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderWatchNextDetails holder, int position) {

        if(mtedId<8) {
            holder.setTedsWatchData(mtalksVos.get(mtedId++));
        }
        else {
            holder.setTedsWatchData(mtalksVos.get(position));
        }

    }


    @Override
    public int getItemCount() {
        return mtalksVos.size()-9;
    }

    public void setMtalksVos(List<TalksVo> mtalksVos,String tedId) {
        this.mtalksVos = mtalksVos;
        mtedId = Integer.parseInt(tedId);
        notifyDataSetChanged();
    }
}
