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

public class GoodsAdapter_mo extends BaseQuickAdapter<Goods.ResultBean.MlssBean.CommodityListBeanXX,BaseViewHolder> {
    public GoodsAdapter_mo(int layoutResId, @Nullable List<Goods.ResultBean.MlssBean.CommodityListBeanXX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Goods.ResultBean.MlssBean.CommodityListBeanXX item) {
        helper.setText(R.id.text3,item.getCommodityName());
        helper.setText(R.id.text4,item.getPrice()+"");
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.image2);
        simpleDraweeView.setImageURI(item.getMasterPic());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,item.getCommodityId()+"",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
