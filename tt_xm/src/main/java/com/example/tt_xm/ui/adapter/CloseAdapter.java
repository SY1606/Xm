package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.ShopCar;
import com.example.tt_xm.ui.weight.Calculate;

import java.util.List;

public class CloseAdapter extends BaseQuickAdapter<ShopCar.ResultBean,BaseViewHolder> {


    public CloseAdapter(int layoutResId, @Nullable List<ShopCar.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCar.ResultBean item) {
        helper.setText(R.id.close_tvname, item.getCommodityName());
        helper.setText(R.id.close_tvprice, item.getPrice() + "");
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.close_image));

        Calculate calculator = helper.getView(R.id.settlement_calcuator);
        /*calculator.setOnCalculatorClickListener(new Calculator.OnCalculatorClickListener() {
            @Override
           public void jia(int num) {
                item.setDefoultNumber(num);


            }
            @Override
           public void jian(int num) {
               item.setDefoultNumber(num);
            }
       });
    }*/

    }
}
