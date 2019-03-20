package com.example.tt_xm.di.model;

import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.bean.Ufo;
import com.example.tt_xm.di.contract.UfoContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UfoModel implements UfoContract.UfoModel {
    @Override
    public void containData(String sessionId,int userId,int page, int count, final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Ufo)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Ufo> ufoObservable = apiService.getUFOContent(sessionId,userId,page,count);
        ufoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Ufo>() {
                    @Override
                    public void accept(Ufo ufo) throws Exception {
                        callBack.OnCallBack(ufo);
                    }
                });
    }
}
