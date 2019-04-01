package com.example.tt_xm.ui.activity_two;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.MyTracks;
import com.example.tt_xm.di.contract.MyTracksContract;
import com.example.tt_xm.di.presenter.MyTracksPresenter;
import com.example.tt_xm.ui.adapter.MyTracksAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MyTracksActivity extends AppCompatActivity implements MyTracksContract.MyTracksView {

    private MyTracksContract.MyTracksPresenter myTracksPresenter;
    int page=1;
    int count=10;
    private RecyclerView my_tracks_rlv;
    private SmartRefreshLayout smart;
    private MyTracksAdapter myTracksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_my_tracks);
        smart = findViewById(R.id.smart);
        my_tracks_rlv = findViewById(R.id.my_tracks_rlv);

        myTracksPresenter = new MyTracksPresenter();
        myTracksPresenter.attacheView(this);
        myTracksPresenter.requestDatas(page,count);

        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                myTracksPresenter.requestDatas(page,count);
                refreshLayout.finishRefresh(1000);
                myTracksAdapter.notifyDataSetChanged();
                smart.finishRefresh(true);

            }
        });
    }

    @Override
    public void showDatass(String message) {
        Gson gson = new Gson();
        MyTracks myTracks = gson.fromJson(message,MyTracks.class);
        List<MyTracks.ResultBean> result = myTracks.getResult();
//        int size = result.size();
//        Toast.makeText(MyTracksActivity.this,size+"",Toast.LENGTH_SHORT).show();
//        ArrayList<MyTracks.ResultBean> beans = (ArrayList<MyTracks.ResultBean>) myTracks.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyTracksActivity.this,2,GridLayoutManager.VERTICAL,false);
        my_tracks_rlv.setLayoutManager(gridLayoutManager);
        myTracksAdapter = new MyTracksAdapter(R.layout.mytracks_item,result);
        my_tracks_rlv.setAdapter(myTracksAdapter);
    }
}
