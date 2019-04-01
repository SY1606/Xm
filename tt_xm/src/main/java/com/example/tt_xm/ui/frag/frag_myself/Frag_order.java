package com.example.tt_xm.ui.frag.frag_myself;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Quorder;
import com.example.tt_xm.di.contract.QuorderContract;
import com.example.tt_xm.di.presenter.QuorderPresenter;
import com.example.tt_xm.ui.adapter.QuorderAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class Frag_order extends Fragment implements QuorderContract.QuorderView {

    private QuorderContract.QuorderPresenter quorderPresenter;
    private RecyclerView all_recycleview;
    int page = 1;
    int count = 5;
    private QuorderAdapter quorderAdapter;
    private SwipeRefreshLayout smart_order;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_frag,container,false);
        all_recycleview = view.findViewById(R.id.all_recycleview);
        smart_order = view.findViewById(R.id.smart_order);
        quorderPresenter = new QuorderPresenter();
        quorderPresenter.attahView(this);
        quorderPresenter.requestData();

        //刷新
        smart_order.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=2;
                quorderPresenter.requestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smart_order.setRefreshing(false);
                    }
                },1000);
            }
        });

        return view;
    }

    @Override
    public void showData(String requestData) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        all_recycleview.setLayoutManager(linearLayoutManager);
        Gson gson = new Gson();
        Quorder quorder = gson.fromJson(requestData, Quorder.class);
        List<Quorder.OrderListBean> detailList = quorder.getOrderList();
        quorderAdapter = new QuorderAdapter(R.layout.all_order_item_layout, detailList);
        all_recycleview.setAdapter(quorderAdapter);
    }
}
