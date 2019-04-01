package com.example.tt_xm.di.model;

import android.util.Log;

import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.app.MyApp;
import com.example.tt_xm.data.app.UserBean;
import com.example.tt_xm.data.bean.Search;
import com.example.tt_xm.di.contract.SendContract;
import com.example.tt_xm.greenDao.DaoSession;
import com.example.tt_xm.greenDao.UserBeanDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendModel implements SendContract.SendModel {

    @Override
    public void containData(int commodityId, String content, MultipartBody.Part image, final CallBack callBack) {
        final MyApp myApp = new MyApp();
        DaoSession daoSession = myApp.getDaoSession();
        UserBeanDao userBeanDao = daoSession.getUserBeanDao();
        List<UserBean> users = userBeanDao.loadAll();
        int size = users.size();
        UserBean userBean = users.get(size-1);
        //String sessionId = userBean.getSessionId();
        //int userId = userBean.getUserId();
        //Log.e("sessionId",sessionId);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Send)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> bodyObservable = apiService.getSendContent(userBean.getUserId(),userBean.getSessionId(),commodityId,content,image);

        bodyObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        String requesData =  responseBody.string().toString();
                        callBack.onCallBack(requesData);
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String errorMsg = throwable.getMessage().toString();
                        Log.i("jgq","失败了"+errorMsg);
                    }
                });
    }
}
