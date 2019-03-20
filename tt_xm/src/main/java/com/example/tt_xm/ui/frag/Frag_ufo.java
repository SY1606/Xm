package com.example.tt_xm.ui.frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Ufo;
import com.example.tt_xm.di.contract.UfoContract;
import com.example.tt_xm.di.presenter.UfoPresenter;
import com.example.tt_xm.ui.activity.SendActivity;
import com.example.tt_xm.ui.adapter.UfoAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class Frag_ufo extends Fragment implements UfoContract.UfoView {

    private UfoContract.UfoPresenter ufoPresenter;
    int page=1;
    int count = 10;
    private int userId;
    private String sessionId;
    private RecyclerView ufo_recy;
    private SmartRefreshLayout smart;
    private TextView send;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_ufo,container,false);
        ufo_recy = view.findViewById(R.id.ufo_recy);
        smart = view.findViewById(R.id.smart);
        send = view.findViewById(R.id.send);

        //发布圈子
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SendActivity.class);
                startActivity(intent);
            }
        });
        //圈子
        ufoPresenter = new UfoPresenter();
        ufoPresenter.attachView(this);

        //接受userId,sessionId
        SharedPreferences sp = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        final int userId = sp.getInt("id",0);
        final String sessionId = sp.getString("session","");

        ufoPresenter.requestData(sessionId,userId,page,count);

        //上拉刷新
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                ufoPresenter.requestData(sessionId,userId,page, count);
                refreshLayout.finishRefresh(1000);
            }
        });
        //下拉加载
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                ufoPresenter.requestData(sessionId,userId,page, count);
                refreshLayout.finishLoadMore(1000);
            }
        });

        return view;
    }

    @Override
    public void showData(Ufo ufo) {
        List<Ufo.ResultBean> ufos = ufo.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        ufo_recy.setLayoutManager(linearLayoutManager);

        UfoAdapter ufoAdapter = new UfoAdapter(R.layout.ufo_item,ufos);
        ufo_recy.setAdapter(ufoAdapter);
    }
}
