package com.example.tt_xm.di.model;

import android.util.Log;

import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.bean.Search;
import com.example.tt_xm.di.contract.SearchContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchModel implements SearchContract.SearchModel {
    @Override
    public void containSearchModel(String name, int page, int count, final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Searchs)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Search> searchObservable = apiService.getSearchData(name,page,count);

        searchObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<Search>() {
                   @Override
                   public void accept(Search search) throws Exception {
                       callBack.responseData(search);
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
