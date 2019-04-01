package com.example.tt_xm.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Address;
import com.example.tt_xm.di.contract.DefaultContract;
import com.example.tt_xm.di.presenter.DefaultPresenter;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<Address.ResultBean,BaseViewHolder> implements DefaultContract.DefaultView {


    private DefaultContract.DefaultPresenter defaultPresenter;

    public AddressAdapter(int layoutResId, @Nullable List<Address.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Address.ResultBean item) {
        helper.setText(R.id.name,item.getRealName());
        helper.setText(R.id.telephone,item.getPhone());
        helper.setText(R.id.address,item.getAddress());

        defaultPresenter = new DefaultPresenter();
        defaultPresenter.attachView(this);

       RadioButton default_adress_radio = helper.getView(R.id.default_adress_radio);
        default_adress_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.getId();
                defaultPresenter.requestData(id);
            }
        });
    }


    @Override
    public void showData(String requestData) {
        Toast.makeText(mContext,requestData,Toast.LENGTH_SHORT).show();
    }
}
