package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Quorder;

import java.util.List;

public class QuorderDetailAdapter extends BaseQuickAdapter<Quorder.OrderListBean.DetailListBean,BaseViewHolder> {


    public QuorderDetailAdapter(int layoutResId, @Nullable List<Quorder.OrderListBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Quorder.OrderListBean.DetailListBean item) {
        helper.setText(R.id.item_name,item.getCommodityName());
        helper.setText(R.id.item_price,item.getCommodityPrice()+"");
        String imsge = item.getCommodityPic().split("\\,")[0].replace("https","http");
        Glide.with(mContext).load(imsge).into((ImageView) helper.getView(R.id.item_image));

    }
}
