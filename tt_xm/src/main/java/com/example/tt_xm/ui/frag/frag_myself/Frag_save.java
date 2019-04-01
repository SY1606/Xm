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
import android.widget.LinearLayout;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Quorder;
import com.example.tt_xm.di.contract.WaitSaveContract;
import com.example.tt_xm.di.presenter.WaitSavePresenter;
import com.example.tt_xm.ui.adapter.WaitSaveFAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_save extends Fragment implements WaitSaveContract.WaitSaveView {


    @BindView(R.id.recy_save)
    RecyclerView recySave;

    Unbinder unbinder;
    private WaitSaveContract.WaitSavePresenter waitSavePresenter;
    int page=1;
    private WaitSaveFAdapter waitSaveFAdapter;
    private SwipeRefreshLayout save_smart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.save_frag, container, false);

        waitSavePresenter = new WaitSavePresenter();
        waitSavePresenter.attahView(this);
        waitSavePresenter.requestData();
        unbinder = ButterKnife.bind(this, view);
        save_smart = view.findViewById(R.id.save_smart);

        save_smart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=2;
                waitSavePresenter.requestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        save_smart.setRefreshing(false);
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

        List<Quorder.OrderListBean> list = quorder.getOrderList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recySave.setLayoutManager(linearLayoutManager);
        waitSaveFAdapter = new WaitSaveFAdapter(R.layout.watit_save_f, list);
        recySave.setAdapter(waitSaveFAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
