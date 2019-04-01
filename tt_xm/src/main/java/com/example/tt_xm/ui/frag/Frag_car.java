package com.example.tt_xm.ui.frag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.ShopCar;
import com.example.tt_xm.di.contract.ShopCarContract;
import com.example.tt_xm.di.presenter.ShopCarPresenterImpl;
import com.example.tt_xm.ui.activity.CloseActivity;
import com.example.tt_xm.ui.adapter.BusinessAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_car extends Fragment implements ShopCarContract.ShopCarView, View.OnClickListener {


    @BindView(R.id.btn_jiesuan)
    Button btnJiesuan;
    @BindView(R.id.cb_All)
    CheckBox cbAll;
    @BindView(R.id.query_text)
    TextView queryText;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.zongjia)
    TextView zongjia;
    @BindView(R.id.ShopRecycler)
    RecyclerView ShopRecycler;
    Unbinder unbinder;
    private ShopCarContract.ShopCarPresenter shopCarPresenter;

    int page = 1;
    int count = 5;

    private BusinessAdapter businessAdapter;
    private List<ShopCar.ResultBean> result;
    private List<ShopCar.ResultBean> listshop;
    private ShopCar shopCar;
    private SwipeRefreshLayout smart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_car, container, false);
        smart = view.findViewById(R.id.smart);
        unbinder = ButterKnife.bind(this, view);

        shopCarPresenter = new ShopCarPresenterImpl();
        shopCarPresenter.attachView(this);

        //全选

        /*smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                shopCarPresenter.requestData();
                refreshLayout.finishRefresh(1000);
                smart.finishRefresh(true);
            }
        });*/

        smart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=2;
                shopCarPresenter.requestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smart.setRefreshing(false);
                    }
                },1000);
            }
        });

        cbAll.setOnCheckedChangeListener(null);
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < result.size(); i++) {
                    result.get(i).setFatherChecked(cbAll.isChecked());
                }
                calculateTotalCount();
                businessAdapter.notifyDataSetChanged();
            }
        });
        return view;

    }


    @Override
    public void showData(final String requsetData) {
        /*List<ShopCar> list = new ArrayList<>();
        for (int i=0;i<shopCar.getResult().size();i++){
            list.add(new ShopCar(shopCar.getResult().get(i).getCommodityId(),shopCar.getResult().get(i).getCount());
        }*/

        Gson gson = new Gson();
        shopCar = gson.fromJson(requsetData, ShopCar.class);
        //获取商家数据集合
        result = shopCar.getResult();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ShopRecycler.setLayoutManager(linearLayoutManager);
        //设置适配器
        businessAdapter = new BusinessAdapter(R.layout.shop_item_layout, result);
        ShopRecycler.setAdapter(businessAdapter);


        //去结算
        btnJiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listshop = new ArrayList<>();
                for (int i = 0; i < shopCar.getResult().size(); i++) {
                    if (shopCar.getResult().get(i).getFatherChecked()) {
                        listshop.add(new ShopCar.ResultBean(shopCar.getResult().get(i).getCommodityId(),
                                shopCar.getResult().get(i).getCommodityName(),
                                shopCar.getResult().get(i).getPic(),
                                shopCar.getResult().get(i).getPrice(),
                                shopCar.getResult().get(i).getCount(),
                                shopCar.getResult().get(i).getFatherChecked(),
                                shopCar.getResult().get(i).getDefoultNumber()
                        ));
                    }
                }

                if (listshop.size() == 0) {
                    Toast.makeText(getActivity(), "请选择要购买的商品哦~", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), CloseActivity.class);
                    intent.putExtra("list", (Serializable) listshop);
                    startActivity(intent);
                }

            }
        });

        businessAdapter.setOnBusinessClickListener(new BusinessAdapter.OnBusinessClickListener() {
            @Override
            public void onCallBack() {
                boolean list = true;
                for (int i = 0; i < result.size(); i++) {
                    boolean fatherChecked = result.get(i).getFatherChecked();
                    list = list & fatherChecked;
                }
                cbAll.setChecked(list);
                //计算总价
                calculateTotalCount();
                businessAdapter.notifyDataSetChanged();
            }

        });


    }


    private void calculateTotalCount() {
        int totalcount = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getFatherChecked()) {
                int price = result.get(i).getPrice();
                int defoultNumber = result.get(i).getDefoultNumber();
                int goodprice = price * defoultNumber;
                totalcount = totalcount + goodprice;
            }
        }
        zongjia.setText("总价是:¥" + totalcount + ".00");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        shopCarPresenter.detachView(this);
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

    }
}
