package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.ShopCar;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ShopCar.ResultBean,BaseViewHolder> {
    public ShopAdapter(int layoutResId, @Nullable List<ShopCar.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCar.ResultBean item) {
        helper.setText(R.id.text_goodsName,item.getCommodityName());
        helper.setText(R.id.text_goodsPrice,item.getPrice()+"");
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.image_goods);
        simpleDraweeView.setImageURI(item.getPic());
    }
}
