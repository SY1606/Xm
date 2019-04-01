package com.example.tt_xm.ui.frag.frag_myself;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Corder;
import com.example.tt_xm.data.bean.Quorder;
import com.example.tt_xm.di.contract.WaitPayContract;
import com.example.tt_xm.di.presenter.WaitPayPresenter;
import com.example.tt_xm.ui.adapter.ObligationAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_pay extends Fragment implements WaitPayContract.WaitPayView {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    Unbinder unbinder;
    private WaitPayContract.WaitPayPresenter waitPayPresenter;
    int page =1;
    private ObligationAdapter obligationAdapter;
    private SwipeRefreshLayout smart_pay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_frag, container, false);
        smart_pay = view.findViewById(R.id.smart_pay);
        waitPayPresenter = new WaitPayPresenter();
        waitPayPresenter.attahView(this);

        unbinder = ButterKnife.bind(this, view);

        //刷新
        smart_pay.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=2;
                waitPayPresenter.requestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smart_pay.setRefreshing(false);
                    }
                },1000);
            }
        });

        return view;
    }

    @Override
    public void showData(String requestData) {
        Log.d("ObligationFragment", requestData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(linearLayoutManager);
        Gson gson = new Gson();
        Quorder quorder = gson.fromJson(requestData, Quorder.class);
        List<Quorder.OrderListBean> detailList = quorder.getOrderList();
        obligationAdapter = new ObligationAdapter(R.layout.obligation_item_layout, detailList);
        recycleview.setAdapter(obligationAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
