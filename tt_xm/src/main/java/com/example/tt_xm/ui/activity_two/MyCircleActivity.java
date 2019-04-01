package com.example.tt_xm.ui.activity_two;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.MyCircle;
import com.example.tt_xm.di.contract.MyCircleContract;
import com.example.tt_xm.di.presenter.MyCirclePresenter;
import com.example.tt_xm.ui.adapter.MyCircleAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MyCircleActivity extends AppCompatActivity implements MyCircleContract.MyCircleView {

    private MyCircleContract.MyCirclePresenter myCirclePresenter;
    private RecyclerView my_circle_rlv;
    int page = 1;
    int count =5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);

        my_circle_rlv = findViewById(R.id.my_circle_rlv);

        myCirclePresenter = new MyCirclePresenter();
        myCirclePresenter.attacheView(this);
        myCirclePresenter.requestDatas(page,count);
    }

    @Override
    public void showDatass(String message) {
        Gson gson = new Gson();
        MyCircle myCircle = gson.fromJson(message,MyCircle.class);
        ArrayList<MyCircle.ResultBean> beans = (ArrayList<MyCircle.ResultBean>) myCircle.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        my_circle_rlv.setLayoutManager(linearLayoutManager);
        MyCircleAdapter myCircleAdapter = new MyCircleAdapter(R.layout.mycircle_item,beans);
        my_circle_rlv.setAdapter(myCircleAdapter);
    }
}
