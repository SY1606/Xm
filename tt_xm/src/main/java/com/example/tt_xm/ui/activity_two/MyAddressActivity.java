package com.example.tt_xm.ui.activity_two;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Address;
import com.example.tt_xm.di.contract.AddressContract;
import com.example.tt_xm.di.presenter.AddressPresenterImpl;
import com.example.tt_xm.ui.adapter.AddressAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

public class MyAddressActivity extends AppCompatActivity implements AddressContract.AddressView {

    private Button adds;
    private RecyclerView recyclerView;
    private AddressContract.AddressPresenter addressPresenter;
    private TextView finish;
    private SmartRefreshLayout smart;
    int page=1;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        recyclerView = findViewById(R.id.recyclerView);

        addressPresenter = new AddressPresenterImpl();
        addressPresenter.attacheView(this);
        addressPresenter.requestData();
        adds = findViewById(R.id.adds);
        finish = findViewById(R.id.finish);
        smart = findViewById(R.id.smart);

        //完成关闭页面
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //添加新的地址
        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressActivity.this,NewAddressActivity.class);
                startActivity(intent);
            }
        });

        //刷新
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                addressPresenter.requestData();
                refreshLayout.finishRefresh(1000);
                addressAdapter.notifyDataSetChanged();
                smart.finishRefresh(true);

            }
        });
    }

    @Override
    public void showDatass(String message) {
        Gson gson = new Gson();
        Address address = gson.fromJson(message,Address.class);
        ArrayList<Address.ResultBean> beans = (ArrayList<Address.ResultBean>) address.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        addressAdapter = new AddressAdapter(R.layout.item_my_address,beans);
        recyclerView.setAdapter(addressAdapter);
    }
}
