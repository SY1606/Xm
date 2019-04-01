package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.app.MyApp;
import com.example.tt_xm.data.app.UserBean;
import com.example.tt_xm.data.bean.Quorder;
import com.example.tt_xm.data.bean.Shou;
import com.example.tt_xm.di.contract.ShouContract;
import com.example.tt_xm.di.presenter.ShouPresenter;
import com.example.tt_xm.greenDao.DaoSession;
import com.example.tt_xm.greenDao.UserBeanDao;
import com.example.tt_xm.ui.activity.DetailsActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WaitSaveFAdapter extends BaseQuickAdapter<Quorder.OrderListBean,BaseViewHolder> implements ShouContract.ShouView {
    private Button sure_button;
     String orderId;
    private ShouContract.ShouPresenter shouPresenter;

    public WaitSaveFAdapter(int layoutResId, @Nullable List<Quorder.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Quorder.OrderListBean item) {
        shouPresenter = new ShouPresenter();
        shouPresenter.attachView(this);


        helper.setText(R.id.mark,item.getOrderId());
        helper.setText(R.id.unit,item.getExpressCompName());
        helper.setText(R.id.wait_num,item.getExpressSn());

        final RecyclerView recyclertitle = helper.getView(R.id.recycle_title);
        List<Quorder.OrderListBean.DetailListBean> detailList = item.getDetailList();
        WaitSaveAdapter waitItemAdapter = new WaitSaveAdapter(R.layout.wait_save,detailList);
        recyclertitle.setAdapter(waitItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclertitle.setLayoutManager(linearLayoutManager);

        //点击确认收货
        sure_button = helper.itemView.findViewById(R.id.sure_button);

        sure_button.setOnClickListener(new View.OnClickListener() {

            private Quorder.OrderListBean orderListBean;

            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"aaa",Toast.LENGTH_SHORT).show();
                orderListBean = new Quorder.OrderListBean();
                String orderId = orderListBean.getOrderId();
                shouPresenter.requestData(orderId);
            }
        });
    }

    @Override
    public void showData(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Shou shou = gson.fromJson(message,Shou.class);
        String status = shou.getStatus();
        if (status.equals("0000")) {
            Toast.makeText(mContext, "收获成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "收获失败", Toast.LENGTH_SHORT).show();
        }
    }
}
