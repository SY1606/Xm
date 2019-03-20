package com.example.tt_xm.di.contract;

public interface LoginContract {
    //这个是登录的
    public interface LoginView{
        public void showData(String responseData);
    }

    public interface LoginPresenter<LoginView>{

        public void attachView(LoginView loginView);
        public void detachView(LoginView loginView);

        public void requestLoginData(String name,String password);
    }

    public interface LoginModel{

        public void containLoginData(String name,String password,CallBack callBack);

        public interface CallBack{
            public void responseData(String reponseData);
        }
    }
}
