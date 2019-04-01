package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Goods;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<Goods.ResultBean.RxxpBean.CommodityListBean,BaseViewHolder> {
    public GoodsAdapter(int layoutResId, @Nullable List<Goods.ResultBean.RxxpBean.CommodityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Goods.ResultBean.RxxpBean.CommodityListBean item) {
        helper.setText(R.id.text1,item.getPrice()+"");
        helper.setText(R.id.text2,item.getCommodityName());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.image1);
        simpleDraweeView.setImageURI(item.getMasterPic());


    }
}
