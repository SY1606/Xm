package com.example.tt_xm.di.contract;

import com.example.tt_xm.data.bean.Search;

public interface SearchContract {

    //v层
    public interface SearchView{
        public void searchData(Search search);
    }
    ;
    //p层
    public interface SearchPresenter<SearchView>{
        //绑定
        public void attach(SearchView searchView);

        //解绑
        public void deach(SearchView searchView);

        //传值
        public void requestData(String name, int page, int count);

    }
    //m层
    public interface SearchModel{
        public void containSearchModel(String name,int page, int count, CallBack callBack);
        //接口
        public interface CallBack{
            public void responseData(Search search);
        }
    }
}
