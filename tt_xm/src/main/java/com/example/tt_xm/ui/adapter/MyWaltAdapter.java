package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Wallet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyWaltAdapter extends BaseQuickAdapter<Wallet.ResultBean.DetailListBean,BaseViewHolder> {


    public MyWaltAdapter(int layoutResId, @Nullable List<Wallet.ResultBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wallet.ResultBean.DetailListBean item) {
        helper.setText(R.id.qianbao_price,item.getAmount()+"");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(
                new Date(item.getConsumerTime()));
        helper.setText(R.id.qianbao_time, date);
    }
    }

