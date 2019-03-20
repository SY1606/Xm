package com.example.tt_xm.ui.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Banner;
import com.example.tt_xm.data.bean.Goods;
import com.example.tt_xm.di.contract.BannerContract;
import com.example.tt_xm.di.contract.GoodsContract;
import com.example.tt_xm.di.presenter.BannerPresenter;
import com.example.tt_xm.di.presenter.GoodsPresenter;
import com.example.tt_xm.ui.activity.DetailsActivity;
import com.example.tt_xm.ui.activity.SearchActivity;
import com.example.tt_xm.ui.adapter.GoodsAdapter;
import com.example.tt_xm.ui.adapter.GoodsAdapter_mo;
import com.example.tt_xm.ui.adapter.GoodsAdapter_pin;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_home extends Fragment implements GoodsContract.GoodView,BannerContract.BannerView {
    @BindView(R.id.cai)
    ImageView cai;
    @BindView(R.id.edit_sou)
    EditText editSou;
    @BindView(R.id.text_sou)
    Button textSou;
    @BindView(R.id.banner)
    MZBannerView mzBannerView;
    @BindView(R.id.re)
    ImageView re;
    @BindView(R.id.recy1)
    RecyclerView recy1;
    @BindView(R.id.mo)
    ImageView mo;
    @BindView(R.id.aa)
    ImageView aa;
    @BindView(R.id.recy3)
    RecyclerView recy3;
    Unbinder unbinder;
    private GoodsContract.GoodPresenter goodPresenter;
    private RecyclerView recy2;
    private BannerContract.BannerPresenter bannerPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        Fresco.initialize(getActivity());
        recy2 = view.findViewById(R.id.recy2);

        //轮播图
        bannerPresenter = new BannerPresenter();
        bannerPresenter.attachView(this);
        bannerPresenter.requestData();

        //商品
        goodPresenter = new GoodsPresenter();
        goodPresenter.attachView(this);
        goodPresenter.requestDatas();

        //点击搜索框跳转到查询页面
        editSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //第一个商品
    @Override
    public void showDatas(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Goods goods = gson.fromJson(message,Goods.class);
                final ArrayList<Goods.ResultBean.RxxpBean.CommodityListBean> list = (ArrayList<Goods.ResultBean.RxxpBean.CommodityListBean>) goods.getResult().getRxxp().getCommodityList();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
                recy1.setLayoutManager(gridLayoutManager);
                GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_hot,list);
                recy1.setAdapter(goodsAdapter);

                //跳转到详情页面
                recy1.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getActivity(),DetailsActivity.class);
                        intent.putExtra("cid",list.get(position).getCommodityId());
                        startActivity(intent);
                    }
                });
            }
        });

        //第二个商品
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Goods goods = gson.fromJson(message,Goods.class);
                final ArrayList<Goods.ResultBean.MlssBean.CommodityListBeanXX> list = (ArrayList<Goods.ResultBean.MlssBean.CommodityListBeanXX>) goods.getResult().getMlss().getCommodityList();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recy2.setLayoutManager(linearLayoutManager);
                GoodsAdapter_mo goodsAdapter_mo = new GoodsAdapter_mo(R.layout.item_mo,list);
                recy2.setAdapter(goodsAdapter_mo);

                recy2.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getActivity(),DetailsActivity.class);
                        intent.putExtra("cid",list.get(position).getCommodityId());
                        startActivity(intent);
                    }
                });
            }
        });

        //第三个商品
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Goods goods = gson.fromJson(message,Goods.class);
                final ArrayList<Goods.ResultBean.PzshBean.CommodityListBeanX> list = (ArrayList<Goods.ResultBean.PzshBean.CommodityListBeanX>) goods.getResult().getPzsh().getCommodityList();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
                recy3.setLayoutManager(gridLayoutManager);
                GoodsAdapter_pin goodsAdapter_pin = new GoodsAdapter_pin(R.layout.item_pin,list);
                recy3.setAdapter(goodsAdapter_pin);


                recy3.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getActivity(),DetailsActivity.class);
                        intent.putExtra("cid",list.get(position).getCommodityId());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        goodPresenter.detachView(this);
    }

    //轮播图
    @Override
    public void showData(String responseData) {
        Gson gson = new Gson();
      Banner banners = gson.fromJson(responseData, Banner.class);
        mzBannerView.setPages(banners.getResult(), new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }
    public static class BannerViewHolder implements MZViewHolder<Banner.ResultBean> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }
        @Override
        public void onBind(Context context, int i, Banner.ResultBean resultBean) {
            //设置图片
            Glide.with(context).load(resultBean.getImageUrl()).into(mImageView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mzBannerView.pause();

    }

    @Override
    public void onResume() {
        super.onResume();
        mzBannerView.start();

    }
}
