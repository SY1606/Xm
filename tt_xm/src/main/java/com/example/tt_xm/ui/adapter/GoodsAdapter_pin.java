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

public class GoodsAdapter_pin extends BaseQuickAdapter<Goods.ResultBean.PzshBean.CommodityListBeanX,BaseViewHolder> {
    public GoodsAdapter_pin(int layoutResId, @Nullable List<Goods.ResultBean.PzshBean.CommodityListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Goods.ResultBean.PzshBean.CommodityListBeanX item) {
            helper.setText(R.id.text5,item.getCommodityName());
            helper.setText(R.id.text6,item.getPrice()+"");
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.image3);
        simpleDraweeView.setImageURI(item.getMasterPic());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,item.getCommodityId()+"",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
