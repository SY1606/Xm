package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Quorder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class PingAdapter extends BaseQuickAdapter<Quorder.OrderListBean.DetailListBean,BaseViewHolder> {
    public PingAdapter(int layoutResId, @Nullable List<Quorder.OrderListBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Quorder.OrderListBean.DetailListBean item) {
        helper.setText(R.id.remait_name,item.getCommodityName());
        helper.setText(R.id.remait_price,item.getCommodityPrice()+"");

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.remait_image);
        simpleDraweeView.setImageURI(item.getCommodityPic());
    }
}
