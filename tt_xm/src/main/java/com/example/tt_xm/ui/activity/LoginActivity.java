package com.example.tt_xm.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.app.MyApp;
import com.example.tt_xm.data.bean.Login;
import com.example.tt_xm.data.app.UserBean;
import com.example.tt_xm.data.net.ScreenUtil;
import com.example.tt_xm.data.net.StatusBarUtil;
import com.example.tt_xm.di.contract.LoginContract;
import com.example.tt_xm.di.presenter.LoginPresenter;
import com.example.tt_xm.greenDao.DaoSession;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.login_layout)
    RelativeLayout loginLayout;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.yin)
    CheckBox yin;
    @BindView(R.id.layout_pwd)
    RelativeLayout layoutPwd;
    @BindView(R.id.jizhu)
    CheckBox jizhu;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.login)
    Button login;
    private LoginContract.LoginPresenter loginPresenter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ScreenUtil.resetDensity(LoginActivity.this);

        //沉浸式状态栏
        StatusBarUtil.setRootViewFitsSystemWindows(LoginActivity.this,true);
        StatusBarUtil.setTranslucentStatus(LoginActivity.this);
        if (!StatusBarUtil.setStatusBarDarkTheme(LoginActivity.this, true)) {
            StatusBarUtil.setStatusBarColor(LoginActivity.this,0x55000000);
        }

        //绑定控件
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        //隐藏密码
        yin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else {
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        //记住密码
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if (sp.getBoolean("c1",false)){
            phone.setText(sp.getString("name1",""));
            pwd.setText(sp.getString("password1",""));
            jizhu.setChecked(true);
        }

        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = phone.getText().toString();
                String password = pwd.getText().toString();
                loginPresenter.requestLoginData(name,password);

                //记住密码
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name1",name);
                editor.putString("password1",password);
                editor.putBoolean("c1",jizhu.isChecked());
                editor.commit();

            }
        });

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisActivity.class);
                startActivity(intent);
            }
        });
    }

    //解析登录
    @Override
    public void showData(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,responseData,Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                Login login = gson.fromJson(responseData,Login.class);
                String status = login.getStatus();
                if (status.equals("0000")){
                    Intent intent = new Intent(LoginActivity.this,ShopActivity.class);
                    startActivity(intent);

                    //int userId = login.getResult().getUserId();
                    //String sessionId = login.getResult().getSessionId();

                    MyApp myApp = new MyApp();
                    DaoSession daoSession = myApp.getDaoSession();
                    UserBean userBean = new UserBean();

                    String sessionId = login.getResult().getSessionId();
                    int userId = login.getResult().getUserId();

                    userBean.setSessionId(sessionId);
                    userBean.setUserId(userId);
                    daoSession.insert(userBean);

                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("userId", userId);
                    edit.putString("sessionId", sessionId);
                    edit.commit();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView(this);
    }
}
