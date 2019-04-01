package com.example.tt_xm.di.model;

import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.app.MyApp;
import com.example.tt_xm.data.app.UserBean;
import com.example.tt_xm.di.contract.AddressContract;
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
import retrofit2.converter.gson.GsonConverterFactory;


public class AddressModelImpl implements AddressContract.AddressModel {



    @Override
    public void containData(final CallBack callBack) {
        MyApp myApp = new MyApp();
        DaoSession daoSession = myApp.getDaoSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        List<UserBean> users = userBeanDao.loadAll();
        int size = users.size();
        UserBean userBean = users.get(size-1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.MyAddress)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> observable = apiService.getAddressContent(userBean.getSessionId(),userBean.getUserId());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String responseData = responseBody.string().toString();
                        callBack.CallBackss(responseData);
                    }
                });
    }
}
