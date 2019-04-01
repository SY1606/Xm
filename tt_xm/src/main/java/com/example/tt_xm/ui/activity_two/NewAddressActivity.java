package com.example.tt_xm.ui.activity_two;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.NewAddress;
import com.example.tt_xm.di.contract.NewAddressContract;
import com.example.tt_xm.di.presenter.NewAddressPresenterImpl;
import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewAddressActivity extends AppCompatActivity implements NewAddressContract.NewAddressView {


    @BindView(R.id.names)
    EditText names;
    @BindView(R.id.phones)
    EditText phones;
    @BindView(R.id.region)
    EditText region;
    @BindView(R.id.detailed_address)
    EditText detailedAddress;
    @BindView(R.id.postal)
    EditText postal;
    @BindView(R.id.save)
    Button save;
    private NewAddressContract.NewAddressPresenter newAddressPresenter;
    private CityPicker cp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ButterKnife.bind(this);


        newAddressPresenter = new NewAddressPresenterImpl();
        newAddressPresenter.attachView(this);


        //保存并使用
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收件人
                String name = names.getText().toString().trim();
                //手机号
                String phone = phones.getText().toString().trim();
                //地区
                String diqus = region.getText().toString();
                //详细地址
                String address = detailedAddress.getText().toString();
                //邮政编码
                String youzheng = postal.getText().toString().trim();
                //地区+详细地址
                String addresss = diqus + address;
                newAddressPresenter.requestData(name, phone, addresss, youzheng);
            }
        });

        region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCityPrice();
                cp.show();
            }
        });
    }

    private void MyCityPrice() {
        cp = new CityPicker.Builder(this)
                .textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cp.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                String province = strings[0];
                //市
                String city = strings[1];
                //区。县。（两级联动，必须返回空）
                String district = strings[2];
                //邮证编码
                String code = strings[3];
                region.setText(province +"   "+ city + "   " + district);
            }

            @Override
            public void onCancel() {

            }
        });
    }
    @Override
    public void showData(String message) {
        Toast.makeText(NewAddressActivity.this, "" +message, Toast.LENGTH_LONG).show();

        Gson gson = new Gson();
        NewAddress newAddress = gson.fromJson(message,NewAddress.class);
        String status = newAddress.getStatus();
        if (status.equals("0000")){
            finish();
        }
    }
}
