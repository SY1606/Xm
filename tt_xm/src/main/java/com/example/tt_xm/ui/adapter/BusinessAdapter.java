package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.ShopCar;
import com.example.tt_xm.ui.weight.Calculate;

import java.util.List;

public class BusinessAdapter extends BaseQuickAdapter<ShopCar.ResultBean,BaseViewHolder> {

    OnBusinessClickListener onBusinessClickListener;

    public void setOnBusinessClickListener(OnBusinessClickListener onBusinessClickListener){
        this.onBusinessClickListener = onBusinessClickListener;
    }


    public interface OnBusinessClickListener{
        public void onCallBack();
    }


    public BusinessAdapter(int layoutResId, @Nullable List<ShopCar.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCar.ResultBean item) {
        helper.setText(R.id.child_title,item.getCommodityName());
        helper.setText(R.id.child_price,item.getPrice()+"");
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.child_img));


        item.setDefoultNumber(1);
        final CheckBox cartcheck = helper.getView(R.id.cart_goods_check);
        cartcheck.setOnCheckedChangeListener(null);
        cartcheck.setChecked(item.getFatherChecked());

        //获取加减器的控件
        Calculate calculator = helper.getView(R.id.Calculator);
        calculator.setOnNumberItemClickListener(new Calculate.OnNumberItemClickListener() {
            @Override
            public void jian(int number) {
                item.setDefoultNumber(number);

                onBusinessClickListener.onCallBack();
            }

            @Override
            public void jia(int number) {
                item.setDefoultNumber(number);

                onBusinessClickListener.onCallBack();
            }
        });

        //子条目复选框
        cartcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setFatherChecked(cartcheck.isChecked());
                notifyDataSetChanged();
                onBusinessClickListener.onCallBack();
            }
        });
    }

}
