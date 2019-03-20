package com.example.tt_xm.di.presenter;

import com.example.tt_xm.data.bean.Search;
import com.example.tt_xm.di.contract.SearchContract;
import com.example.tt_xm.di.model.SearchModel;

import java.lang.ref.SoftReference;

public class SearchPresenter implements SearchContract.SearchPresenter<SearchContract.SearchView>{

    SearchContract.SearchView searchView;
    SearchContract.SearchModel searchModel;
    private SoftReference<SearchContract.SearchView> softReference;

    @Override
    public void attach(SearchContract.SearchView searchView) {
        this.searchView = searchView;
        softReference = new SoftReference<>(searchView);
        searchModel = new SearchModel();
    }

    @Override
    public void deach(SearchContract.SearchView SearchView) {
        softReference.clear();
    }

    @Override
    public void requestData(String name, int page, int count) {
        searchModel.containSearchModel(name, page, count, new SearchContract.SearchModel.CallBack() {
            @Override
            public void responseData(Search search) {
                searchView.searchData(search);
            }
        });
    }
}
