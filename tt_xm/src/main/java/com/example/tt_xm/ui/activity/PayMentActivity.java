package com.example.tt_xm.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.PayOrder;
import com.example.tt_xm.di.contract.PayContract;
import com.example.tt_xm.di.presenter.PayPresenter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayMentActivity extends AppCompatActivity implements PayContract.PayView {


    @BindView(R.id.way)
    TextView way;
    @BindView(R.id.balancepaid)
    ImageView balancepaid;
    @BindView(R.id.balancepaid_text)
    TextView balancepaidText;
    @BindView(R.id.alipay)
    ImageView alipay;
    @BindView(R.id.alipay_text)
    TextView alipayText;
    @BindView(R.id.wechat)
    ImageView wechat;
    @BindView(R.id.wechat_text)
    TextView wechatText;
    @BindView(R.id.balancepaid_radio)
    RadioButton balancepaidRadio;
    @BindView(R.id.wechat_radio)
    RadioButton wechatRadio;
    @BindView(R.id.alipay_radio)
    RadioButton alipayRadio;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.xxx)
    ConstraintLayout xxx;
    private PayContract.PayPresenter payPresenter;
    private PopupWindow popupWindow;
    int payType = 1;
    private String orderId;
    private double price;
    private View success;
    private View shibai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ment);
        ButterKnife.bind(this);
        payPresenter = new PayPresenter();
        payPresenter.attahView(this);


        //接受传过来的值
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        price = intent.getDoubleExtra("price", 0.00);

        success = View.inflate(this, R.layout.paysuccess, null);
        shibai = View.inflate(this, R.layout.payshibai, null);

        Button bt_align = shibai.findViewById(R.id.bt_align);
        bt_align.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button bt_main_view = success.findViewById(R.id.bt_main_view);
        bt_main_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button bt_all_view = success.findViewById(R.id.bt_all_view);
        bt_all_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showData(String requestData) {
        Gson gson = new Gson();
        PayOrder payOrder = gson.fromJson(requestData, PayOrder.class);
        String message = payOrder.getMessage();

        if (payType == 1) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            popupWindow = new PopupWindow(success, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.showAsDropDown(way);


        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            popupWindow = new PopupWindow(shibai, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.showAsDropDown(way);
        }
    }

    //支付方式的点击按钮
    @OnClick({R.id.balancepaid_radio, R.id.wechat_radio, R.id.alipay_radio, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.balancepaid_radio:
                payType = 1;
                btnPay.setText("余额支付:" + price + "元");

                break;
            case R.id.wechat_radio:
                payType = 2;
                btnPay.setText("微信支付:" + price + "元");
                break;
            case R.id.alipay_radio:
                payType = 3;
                btnPay.setText("支付宝支付:" + price + "元");
                break;
            case R.id.btn_pay:
                payPresenter.requestData(orderId, payType);
                break;
        }
    }
}
