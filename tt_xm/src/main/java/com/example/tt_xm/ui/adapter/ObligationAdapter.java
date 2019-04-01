package com.example.tt_xm.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Quorder;
import com.example.tt_xm.ui.activity.PayMentActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ObligationAdapter extends BaseQuickAdapter<Quorder.OrderListBean,BaseViewHolder> {

    private int num ;
    private double zprice ;


    public ObligationAdapter(int layoutResId, @Nullable List<Quorder.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Quorder.OrderListBean item) {
        helper.setText(R.id.mark,item.getOrderId());
        //String date = new SimpleDateFormat("yyyy-MM-dd").format(
               // new Date(item.getOrderTime()));
        //helper.setText(R.id.obligation_order_time, date);


        RecyclerView recyclertitle = helper.getView(R.id.recycle_title);
        List<Quorder.OrderListBean.DetailListBean> detailList = item.getDetailList();
        ObligationChildAdapter obligationChildAdapter = new ObligationChildAdapter(R.layout.obligation_child_item,detailList);
        recyclertitle.setAdapter(obligationChildAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclertitle.setLayoutManager(linearLayoutManager);

        Button payment = helper.getView(R.id.payment_button);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PayMentActivity.class);
                intent.putExtra("orderId",item.getOrderId());
                intent.putExtra("price",item.getDetailList().get(0).getCommodityPrice());
                mContext.startActivity(intent);
            }
        });

        num=0;
        zprice=0;
        for (int i = 0; i <item.getDetailList().size() ; i++) {
            int count = item.getDetailList().get(i).getCommodityCount();
            num +=count;
            double price = item.getDetailList().get(i).getCommodityPrice();
            zprice+=price;
        }
        helper.setText(R.id.num_price,"共"+num+"件商品"+",需要付款"+zprice+"元");
    }
}
