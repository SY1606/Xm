package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.ShopCar;
import com.example.tt_xm.ui.weight.Calculate;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ShopCar.ResultBean,BaseViewHolder> {


    OnGoodItemClickListenerClick onGoodItemClickListenerClick;

    public void setOnGoodItemClickListenerClick(OnGoodItemClickListenerClick onGoodItemClickListenerClick) {
        this.onGoodItemClickListenerClick = onGoodItemClickListenerClick;
    }

    public interface OnGoodItemClickListenerClick{
        public void CallBack();
    }

    public ShopAdapter(int layoutResId, @Nullable List<ShopCar.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCar.ResultBean item) {
        helper.setText(R.id.text_goodsName,item.getCommodityName());
        helper.setText(R.id.text_goodsPrice,item.getPrice()+"");


        //避免焦点抢占
        final CheckBox checkbox_goods = helper.getView(R.id.checkbox_goods);
        checkbox_goods.setOnCheckedChangeListener(null);
        checkbox_goods.setChecked(item.getFatherChecked());

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.image_goods);
        simpleDraweeView.setImageURI(item.getPic());


        //子条目
        checkbox_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setFatherChecked(checkbox_goods.isChecked());
                onGoodItemClickListenerClick.CallBack();
            }
        });
        //获取自定义view
        Calculate calculate = helper.getView(R.id.goods_view);

        calculate.setNum(item.getCount());

        calculate.setOnNumberItemClickListener(new Calculate.OnNumberItemClickListener() {
            @Override
            public void jian(int number) {
                item.setCount(number);
                onGoodItemClickListenerClick.CallBack();
            }
            @Override
            public void jia(int number) {
                item.setCount(number);
                onGoodItemClickListenerClick.CallBack();
            }
        });
    }

}
