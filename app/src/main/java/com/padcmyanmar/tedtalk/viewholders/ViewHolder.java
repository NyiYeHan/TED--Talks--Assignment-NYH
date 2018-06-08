package com.padcmyanmar.tedtalk.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.tedtalk.delegate.NewsDelegateTedTalk;


public class ViewHolder extends RecyclerView.ViewHolder {

    private NewsDelegateTedTalk newsDelegateTedTalk;
    public ViewHolder(View itemView, final NewsDelegateTedTalk newsDelegateTedTalk) {

        super(itemView);
        this.newsDelegateTedTalk = newsDelegateTedTalk;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsDelegateTedTalk.onTapView();
            }
        });
    }
}
