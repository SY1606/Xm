package com.example.tt_xm.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Search;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerachAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Search.ResultBean> searchList;

    public void setData(Context context,List<Search.ResultBean> searchList){
        this.context = context;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.textName.setText(searchList.get(i).getCommodityName());
        myHolder.textPrice.setText("￥"+searchList.get(i).getPrice());
        myHolder.textYishou.setText("已售"+searchList.get(i).getCommodityId()+"件");
        myHolder.imageview.setImageURI(Uri.parse(searchList.get(i).getMasterPic()));

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview)
        SimpleDraweeView imageview;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.text_yishou)
        TextView textYishou;

        public MyHolder(View view) {
            super(view);
            ButterKnife.bind(this,itemView);
        }
    }
}
