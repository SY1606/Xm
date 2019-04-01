package com.example.tt_xm.ui.activity_two;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.bean.Login;
import com.example.tt_xm.data.bean.Update;
import com.example.tt_xm.di.contract.LoginContract;
import com.example.tt_xm.di.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateActivity extends AppCompatActivity implements LoginContract.LoginView {

    @BindView(R.id.amend_newname)
    EditText amendNewname;
    @BindView(R.id.amend_btn)
    Button amendBtn;
    private String status;
    private SharedPreferences preferences;
    private LoginContract.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);

        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId1 = preferences.getInt("userId", 0);
        final String sessionId = preferences.getString("sessionId", "");
        final String loginPass = preferences.getString("loginPass", "");
        final String phone = preferences.getString("phone", "");

        final String userId = String.valueOf(userId1);
        String nickName = preferences.getString("nickName", "");




        amendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String amendNewname2 = amendNewname.getText().toString();

                if (amendNewname2.trim().equals("")){
                    finish();
                }

                //修改昵称
                OkGo.<String>put(Constant.UpdateName)
                        .headers("userId", userId)
                        .headers("sessionId", sessionId)
                        .params("nickName", amendNewname2)
                        .execute(new StringCallback() {

                            @Override
                            public void onSuccess(Response<String> response) {
                                String s = response.body().toString();
                                Gson gson = new Gson();
                                Update amendBean = gson.fromJson(s, Update.class);
                                String message = amendBean.getMessage();
                                status = amendBean.getStatus();
                                Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


    }

    @Override
    public void showData(String responseData) {

        Gson gson = new Gson();
        Login login = gson.fromJson(responseData, Login.class);
        Login.ResultBean result = login.getResult();
        String status = login.getStatus();


        if (status.equals("1001")){
            //.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }else {

            int userId = result.getUserId();
            String sessionId = result.getSessionId();
            String nickName = result.getNickName();
            String phone = result.getPhone();
            String headPic = result.getHeadPic();
            int sex = result.getSex();

            SharedPreferences.Editor edit = preferences.edit();
            edit.putInt("userId",userId);
            edit.putString("sessionId",sessionId);
            edit.putString("nickName",nickName);
            edit.putString("phone",phone);
            edit.putString("headPic",headPic);
            edit.putInt("sex",sex);
            edit.commit();
            finish();
        }
    }
}
