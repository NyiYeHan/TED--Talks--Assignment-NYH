package com.padcmyanmar.tedtalk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.tedtalk.R;
import com.padcmyanmar.tedtalk.delegate.NewsDelegateTedTalk;
import com.padcmyanmar.tedtalk.viewholders.ViewHolder;


public class TedAdapter extends RecyclerView.Adapter {
    private NewsDelegateTedTalk newsDelegateTedTalk;

    public TedAdapter(NewsDelegateTedTalk newsDelegateTedTalk){
        this.newsDelegateTedTalk = newsDelegateTedTalk;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());//layout inflater obj
       View view = layoutInflater.inflate(R.layout.activity_list,parent,false);

        return new ViewHolder(view , newsDelegateTedTalk);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
