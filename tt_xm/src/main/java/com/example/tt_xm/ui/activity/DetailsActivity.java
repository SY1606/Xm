package com.example.tt_xm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.app.ImageLoder;
import com.example.tt_xm.data.bean.Details;
import com.example.tt_xm.data.bean.Sync;
import com.example.tt_xm.di.contract.DetailContract;
import com.example.tt_xm.di.contract.SyncContract;
import com.example.tt_xm.di.presenter.DetailPresenter;
import com.example.tt_xm.di.presenter.SyncPresenterImpl;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailContract.DetailsView,SyncContract.SyncView {

    @BindView(R.id.par_banner)
    Banner parBanner;
    @BindView(R.id.details_price)
    TextView detailsPrice;
    @BindView(R.id.details_yiShou)
    TextView detailsYiShou;
    @BindView(R.id.details_title)
    TextView detailsTitle;
    @BindView(R.id.details_weight)
    TextView detailsWeight;
    @BindView(R.id.par_webview)
    WebView parWebview;
    @BindView(R.id.details_image)
    ImageView detailsImage;
    @BindView(R.id.details_introduce)
    TextView detailsIntroduce;
    @BindView(R.id.goods_introduceImg)
    ImageView goodsIntroduceImg;
    @BindView(R.id.adds)
    ImageView adds;
    @BindView(R.id.buy)
    ImageView buy;
    private DetailContract.DetailsPresenter detailsPresenter;
    private Details.ResultBean resultBean;
    private ArrayList<String> imageurilist;
    private SyncContract.SyncPresenter syncPresenter;
    int userId;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        //接受cid
        imageurilist = new ArrayList<>();
        Intent intent = this.getIntent();
        int cid = intent.getIntExtra("cid", 0);

        //详情页面
        detailsPresenter = new DetailPresenter();
        detailsPresenter.attachView(this);
        detailsPresenter.requestData(cid);

        //同步购物车
        syncPresenter = new SyncPresenterImpl();
        syncPresenter.attachView(this);

        SharedPreferences sp = getSharedPreferences("login",Context.MODE_PRIVATE);
        userId = sp.getInt("id",0);
        sessionId = sp.getString("session","");

        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId1 = resultBean.getCommodityId();
                int count = 1;
                syncPresenter.requestData(commodityId1,count);
            }
        });
    }


    //商品详情解析
    @Override
    public void showData(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Details details = gson.fromJson(message, Details.class);
                resultBean = details.getResult();
                String[] split = resultBean.getPicture().split("\\，");
                //将图片集合添加到图片的集合
                for (int i = 0; i < split.length; i++) {
                    imageurilist.add(split[i]);
                }
                parBanner.setImageLoader(new ImageLoder());
                parBanner.setImages(imageurilist);
                parBanner.start();


                //webView网页请求
                String details1 = resultBean.getDetails();
                WebSettings settings = parWebview.getSettings();
                settings.setJavaScriptEnabled(true);
                String js = "<script type=\"text/javascript\">" +

                        "var imgs=document.getElementsByTagName('img');" +
                        "for(var i = 0; i<imgs.length; i++){" +
                        "imgs[i].style.width='100%';" +
                        "imgs[i].style.height='auto';" +
                        "}" +
                        "</script>";
                parWebview.loadDataWithBaseURL(null, details1 + js + "<html></body>", "text/html", "utf-8", null);
                //价格
                detailsPrice.setText("$" + resultBean.getPrice());
                //名字
                detailsTitle.setText(resultBean.getCommodityName());
                //介绍
                detailsIntroduce.setText(resultBean.getDescribe());
                //重量
                detailsWeight.setText(resultBean.getWeight() + "kg");
                //销售数量
                detailsYiShou.setText("已售" + resultBean.getCommentNum() + "件");
            }
        });

    }

    @Override
    public void showDatas(String message) {
        Gson gson = new Gson();
        Sync sync = gson.fromJson(message,Sync.class);
        String status = sync.getStatus();
        if (status.equals("0000")){
            Toast.makeText(DetailsActivity.this,"同步成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(DetailsActivity.this,"同步失败",Toast.LENGTH_SHORT).show();
        }
    }
}
