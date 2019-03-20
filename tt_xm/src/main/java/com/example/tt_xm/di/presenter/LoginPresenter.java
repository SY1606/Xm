package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.LoginContract;
import com.example.tt_xm.di.model.LoginModel;

import java.lang.ref.SoftReference;

public class LoginPresenter implements LoginContract.LoginPresenter<LoginContract.LoginView> {

    LoginContract.LoginModel loginModel;
    LoginContract.LoginView loginView;
    private SoftReference<LoginContract.LoginView> softReference;

    @Override
    public void attachView(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        softReference = new SoftReference<>(loginView);
        loginModel = new LoginModel();
    }

    @Override
    public void detachView(LoginContract.LoginView loginView) {
        softReference.clear();
    }

    @Override
    public void requestLoginData(String name, String password) {
        loginModel.containLoginData(name, password, new LoginContract.LoginModel.CallBack() {
            @Override
            public void responseData(String reponseData) {
                loginView.showData(reponseData);
            }
        });
    }
}
