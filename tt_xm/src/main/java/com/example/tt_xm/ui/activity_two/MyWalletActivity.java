package com.example.tt_xm.ui.activity_two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Wallet;
import com.example.tt_xm.di.contract.WalletContract;
import com.example.tt_xm.di.presenter.WalletPresenter;
import com.example.tt_xm.ui.adapter.MyWaltAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWalletActivity extends AppCompatActivity implements WalletContract.WalletView {

    @BindView(R.id.wallet_back)
    ImageView walletBack;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.view_linev)
    View viewLinev;
    @BindView(R.id.wallet_recycle)
    RecyclerView walletRecycle;
    private WalletContract.WalletPresenter walletPresenter;
    int page = 1;
    int count = 5;
     String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money);
        ButterKnife.bind(this);
        walletPresenter = new WalletPresenter();
        walletPresenter.attachView(this);
        walletPresenter.requestData(page, count);
    }

    @Override
    public void showData(String message) {
        Gson gson = new Gson();
        Wallet wallet = gson.fromJson(message, Wallet.class);
        List<Wallet.ResultBean.DetailListBean> detailList = wallet.getResult().getDetailList();

        int balance = wallet.getResult().getBalance();
        money.setText(balance + ".00");
        //Log.d("MyWaltActivity", requestData);
        orderId = wallet.getResult().getDetailList().get(0).getOrderId();
        MyWaltAdapter myWaltAdapter = new MyWaltAdapter(R.layout.my_walt_item_layout,detailList);
        walletRecycle.setAdapter(myWaltAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        walletRecycle.setLayoutManager(linearLayoutManager);
    }
}
