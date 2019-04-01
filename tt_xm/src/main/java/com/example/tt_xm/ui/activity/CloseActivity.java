package com.example.tt_xm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Address;
import com.example.tt_xm.data.bean.Corder;
import com.example.tt_xm.data.bean.ShopCar;
import com.example.tt_xm.di.contract.AddressContract;
import com.example.tt_xm.di.contract.CorderContract;
import com.example.tt_xm.di.presenter.AddressPresenterImpl;
import com.example.tt_xm.di.presenter.CorderPresenter;
import com.example.tt_xm.ui.adapter.CloseAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CloseActivity extends AppCompatActivity implements AddressContract.AddressView,CorderContract.CorderView {

    @BindView(R.id.address_name)
    TextView addressName;
    @BindView(R.id.address_phone)
    TextView addressPhone;
    @BindView(R.id.address_myaddress)
    TextView addressMyaddress;
    @BindView(R.id.showAddress)
    LinearLayout showAddress;
    @BindView(R.id.settle_showRecyclerView)
    RecyclerView settleShowRecyclerView;
    @BindView(R.id.address_describe_text)
    TextView addressDescribeText;
    @BindView(R.id.address_describe_num)
    TextView addressDescribeNum;
    @BindView(R.id.address_describe_text1)
    TextView addressDescribeText1;
    @BindView(R.id.address_describe_price)
    TextView addressDescribePrice;
    @BindView(R.id.btn_commit_goods)
    Button btnCommitGoods;
    private List<ShopCar.ResultBean> list;
    private int num = 0;
    private double totalPrice = 0.00;
    private AddressContract.AddressPresenter addressPresenter;
    private Address addressBean;
    int addressId;
    private CorderContract.CorderPresenter corderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close);
        ButterKnife.bind(this);

        //收获地址
        addressPresenter = new AddressPresenterImpl();
        addressPresenter.attacheView(this);
        addressPresenter.requestData();

        //订单
        corderPresenter = new CorderPresenter();
        corderPresenter.attachView(this);



        //接受购物车的数据
        Intent intent = getIntent();
        list = (List<ShopCar.ResultBean>) intent.getSerializableExtra("list");
        showGods();
    }


    //收到后展示
    private void showGods() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CloseActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        settleShowRecyclerView.setLayoutManager(linearLayoutManager);
        CloseAdapter closeAdapter = new CloseAdapter(R.layout.close_recycler_item_layout,list);
        settleShowRecyclerView.setAdapter(closeAdapter);

        for (int i = 0; i < list.size(); i++) {
            //获取携带的数量
            int count = list.get(i).getDefoultNumber();
            //数量累加
            num += count;
            //计算价格
            totalPrice += list.get(i).getPrice() * list.get(i).getDefoultNumber();
        }
        //底部商品描述
        addressDescribeNum.setText(num + "");
        addressDescribePrice.setText(totalPrice + "");
    }


    //收获地址赋值
    @Override
    public void showDatass(String message) {
        Gson gson = new Gson();
        addressBean = gson.fromJson(message,Address.class);
        for (int i=0;i<addressBean.getResult().size();i++){
            int whetherDefault = addressBean.getResult().get(i).getWhetherDefault();
            if (whetherDefault == 1) {
                showAddress.setVisibility(View.VISIBLE);
                String address = addressBean.getResult().get(i).getAddress();
                String phone = addressBean.getResult().get(i).getPhone();
                String realName = addressBean.getResult().get(i).getRealName();
                addressId = addressBean.getResult().get(i).getId();
                addressName.setText(realName);
                addressPhone.setText(phone);
                addressMyaddress.setText(address);
            }
        }

        //提交订单
        btnCommitGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressBean.getResult().size() == 0){
                    Toast.makeText(CloseActivity.this, "请先添加收货地址", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    ArrayList<Corder> listcreate = new ArrayList<>();
                    for (int i = 0; i < list.size() ; i++) {
                        Corder corder = new Corder();
                        corder.setAmount(list.get(i).getDefoultNumber());
                        corder.setCommodityId(list.get(i).getCommodityId());
                        listcreate.add(corder);
                    }
                    String order = new Gson().toJson(listcreate);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("orderInfo",order);
                    map.put("totalPrice",totalPrice+"");
                    map.put("addressId",addressId+"");
                    corderPresenter.requestData(map);
                }
            }
        });
    }

    //创建订单
    @Override
    public void showData(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
