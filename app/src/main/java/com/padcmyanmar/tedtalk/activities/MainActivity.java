package com.padcmyanmar.tedtalk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.padcmyanmar.tedtalk.R;
import com.padcmyanmar.tedtalk.adapters.TedAdapter;
import com.padcmyanmar.tedtalk.data.model.TedTalksNewsModel;
import com.padcmyanmar.tedtalk.data.vo.TalksVo;
import com.padcmyanmar.tedtalk.delegate.NewsDelegateTedTalk;
import com.padcmyanmar.tedtalk.events.SuccessGetTedTedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements NewsDelegateTedTalk {

    private TedAdapter tedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvBottom = findViewById(R.id.tv_bottom);
        //getResources().getDimension(R.dimen.margin_card_medium);
        RecyclerView rv = findViewById(R.id.rv_mess);
        tedAdapter = new TedAdapter(this);
        rv.setAdapter(tedAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TedTalksNewsModel.getObj().loadTedNewsList();


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        item.setIcon(R.drawable.ic_more_vert_black_24dp);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapView(TalksVo talksVo) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailActivityTedTalk.class);
        intent.putExtra("talk_id",talksVo.getTedId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNews(SuccessGetTedTedEvent event){
        Log.d("onSuccessGetNews","talks vo list : "+event.getTalksVos());
        tedAdapter.setTedNewsList(event.getTalksVos());


    }
}
