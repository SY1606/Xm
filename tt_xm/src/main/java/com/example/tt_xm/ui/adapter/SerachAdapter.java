package com.example.tt_xm.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Search;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerachAdapter extends BaseQuickAdapter<Search.ResultBean,BaseViewHolder> {

    public SerachAdapter(int layoutResId, @Nullable List<Search.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Search.ResultBean item) {
        helper.setText(R.id.text_names,item.getCommodityName());
        helper.setText(R.id.text_prices,"$"+item.getPrice());
        helper.setText(R.id.text_yishou,"已售"+item.getSaleNum()+"件");
        Glide.with(mContext).load(item.getMasterPic()).into((ImageView) helper.getView(R.id.imageview));

    }
}
