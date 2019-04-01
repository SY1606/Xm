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
import com.example.tt_xm.di.contract.WaitPingContract;
import com.example.tt_xm.di.presenter.WaitPingPresenter;
import com.example.tt_xm.ui.adapter.PingFAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_ping extends Fragment implements WaitPingContract.WaitPingView {



    private WaitPingContract.WaitPingPresenter waitPingPresenter;
    int page=1;
    private RecyclerView recycleview;
    private SwipeRefreshLayout recy_ping;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ping_frag, container, false);

        waitPingPresenter = new WaitPingPresenter();
        waitPingPresenter.attahView(this);

        recy_ping = view.findViewById(R.id.recy_ping);
        recycleview = view.findViewById(R.id.recycleview);

        recy_ping.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=2;
                waitPingPresenter.requestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recy_ping.setRefreshing(false);
                    }
                },1000);
            }
        });

        return view;
    }

    @Override
    public void showData(String requestData) {
        Gson gson = new Gson();
        Quorder quorder = gson.fromJson(requestData, Quorder.class);
        List<Quorder.OrderListBean> orderList = quorder.getOrderList();
        PingFAdapter pingFAdapter = new PingFAdapter(R.layout.ping_frag, orderList);
        recycleview.setAdapter(pingFAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycleview.setLayoutManager(linearLayoutManager);
    }

}
