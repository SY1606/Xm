package com.example.tt_xm.di.contract;

public interface PersonContract {

    //V
    public interface PersonDataView{
        //刷新数据
        public void getPersonDataData(String responseData);

    }

    //P
    public interface PersonDataPresenter<PersonDataView>{

        //绑定
        public void attchPersonDataView(PersonDataView personDataView);

        //解绑
        public void deachPersonDataView(PersonDataView personDataView);


        //m
        public void requestPersonDataData();

    }

    //M
    public interface PersonDataModel{

        //请求
        public void containData(CallBack callBack);

        //接口回调
        public interface CallBack{
            //回显
            public void onCallBacks(String responseData);

        }



    }
}
