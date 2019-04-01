package com.example.tt_xm.di.model;

import android.util.Log;

import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.app.MyApp;
import com.example.tt_xm.data.app.UserBean;
import com.example.tt_xm.di.contract.PersonContract;
import com.example.tt_xm.greenDao.DaoSession;
import com.example.tt_xm.greenDao.UserBeanDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class PersonModel implements PersonContract.PersonDataModel {

    @Override
    public void containData(final CallBack callBack) {
        final MyApp myApp = new MyApp();
        DaoSession daoSession = myApp.getDaoSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        List<UserBean> users = userBeanDao.loadAll();
        int size = users.size();
        UserBean userBean = users.get(size-1);

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.Person)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> observable = apiService.getPersonContent(userBean.getUserId(),userBean.getSessionId());
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String requestData = responseBody.string().toString();
                        callBack.onCallBacks(requestData);
                        Log.d("PersonModel", requestData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
