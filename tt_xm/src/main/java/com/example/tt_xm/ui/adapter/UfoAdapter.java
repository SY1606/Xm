package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Ufo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class UfoAdapter extends BaseQuickAdapter<Ufo.ResultBean,BaseViewHolder> {
    public UfoAdapter(int layoutResId, @Nullable List<Ufo.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Ufo.ResultBean item) {

        helper.setText(R.id.ufo_name,item.getNickName());
        helper.setText(R.id.ufo_time, item.getCreateTime()+"");
        helper.setText(R.id.ufo_em,item.getContent());

        SimpleDraweeView tou_image = helper.getView(R.id.tou_image);
        tou_image.setImageURI(item.getHeadPic());

        SimpleDraweeView ufo_image = helper.getView(R.id.ufo_image);
        ufo_image.setImageURI(item.getImage());

        //判断是否点赞
        final TextView mycircle_type_one_zannum = helper.getView(R.id.text);
        final CheckBox mycircle_type_one_zan = helper.getView(R.id.zan);

        mycircle_type_one_zannum.setText(item.getGreatNum()+"");

        mycircle_type_one_zan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    int i = (item.getGreatNum() + 1);
                    mycircle_type_one_zannum.setText(i+"");
                }else {
                    mycircle_type_one_zannum.setText(item.getGreatNum()+"");
                }
            }
        });

    }
}
