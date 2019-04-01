package com.example.tt_xm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Login;
import com.example.tt_xm.di.contract.RegisContract;
import com.example.tt_xm.di.presenter.RegisPresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisActivity extends AppCompatActivity implements RegisContract.RegisView {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.layout_phone)
    RelativeLayout layoutPhone;
    @BindView(R.id.ed_yz)
    EditText edYz;
    @BindView(R.id.ed_hq)
    TextView edHq;
    @BindView(R.id.layout_yan)
    RelativeLayout layoutYan;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.ed_yin)
    ImageView edYin;
    @BindView(R.id.layout_pwd)
    RelativeLayout layoutPwd;
    @BindView(R.id.ed_login)
    TextView edLogin;
    @BindView(R.id.ed_zhuce)
    Button edZhuce;
    private RegisContract.RegisPresenter regisPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        ButterKnife.bind(this);

        regisPresenter = new RegisPresenterImpl();
        regisPresenter.attachView(this);
    }

    @Override
    public void regisData(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisActivity.this,"cg"+responseData,Toast.LENGTH_LONG).show();
                Log.e("ii",responseData);
                Gson gson = new Gson();
                Login login = gson.fromJson(responseData,Login.class);
                String status = login.getStatus();
                if (status.equals("0000")){
                    Intent intent = new Intent(RegisActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @OnClick({R.id.ed_login, R.id.ed_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_login:
                Intent intent = new Intent(RegisActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ed_zhuce:
                String mobile = edPhone.getText().toString();
                String password = edPwd.getText().toString();
                regisPresenter.requestRegisData(mobile,password);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        regisPresenter.detachView(this);
    }
}
