package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.MyCircle;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyCircleAdapter extends BaseQuickAdapter<MyCircle.ResultBean,BaseViewHolder> {
    public MyCircleAdapter(int layoutResId, @Nullable List<MyCircle.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCircle.ResultBean item) {
        helper.setText(R.id.item_my_circle_content,item.getNickName());
        helper.setText(R.id.item_my_circle_date,item.getCreateTime()+"");
        helper.setText(R.id.item_my_circle_text_num,item.getGreatNum()+"");

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.item_my_circle_sdv);
        simpleDraweeView.setImageURI(item.getImage());
        Glide.with(mContext).load(item.getHeadPic()).into((ImageView)helper.getView(R.id.item_my_circle_img_prise));

    }
}
