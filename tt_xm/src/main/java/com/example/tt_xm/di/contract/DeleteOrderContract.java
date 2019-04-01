package com.example.tt_xm.di.contract;

public interface DeleteOrderContract {
    public interface DeleteOrderView{
        public void showData(String requestData);
    }


    public interface DeleteOrderDanPresenter<DeleteOrderView>{
        public void attahView(DeleteOrderView deleteOrderView);

        public void deachView(DeleteOrderView deleteOrderView);

        public void requestData(String orderId);
    }


    public interface DeleteOrderDanModel{
        public void containData(String orderId, CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }
}
