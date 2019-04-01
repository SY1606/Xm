package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Quorder;

import java.util.List;

public class PingFAdapter extends BaseQuickAdapter<Quorder.OrderListBean,BaseViewHolder> {

    private RecyclerView recycle_title;

    public PingFAdapter(int layoutResId, @Nullable List<Quorder.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Quorder.OrderListBean item) {

        recycle_title = helper.getView(R.id.recycleview);
        List<Quorder.OrderListBean.DetailListBean> detailList = item.getDetailList();
        PingAdapter pingAdapter = new PingAdapter(R.layout.ping_child,detailList);
        recycle_title.setAdapter(pingAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recycle_title.setLayoutManager(linearLayoutManager);
    }
}
