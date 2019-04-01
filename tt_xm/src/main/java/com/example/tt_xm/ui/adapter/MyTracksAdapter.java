package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.MyTracks;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyTracksAdapter extends BaseQuickAdapter<MyTracks.ResultBean,BaseViewHolder> {

    public MyTracksAdapter(int layoutResId, @Nullable List<MyTracks.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyTracks.ResultBean item) {
        helper.setText(R.id.item_my_tracks_text_content,item.getCommodityName());
        helper.setText(R.id.item_my_tracks_text_price,item.getPrice()+"");
        helper.setText(R.id.item_my_tracks_text_look,item.getBrowseNum()+"");
        helper.setText(R.id.item_my_tracks_text_date,item.getBrowseTime()+"");

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.item_my_tracks_img);
        simpleDraweeView.setImageURI(item.getMasterPic());
    }
}
