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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.data.app.ImageLoder;
import com.example.tt_xm.data.bean.Details;
import com.example.tt_xm.data.bean.Sync;
import com.example.tt_xm.data.net.StatusBarUtil;
import com.example.tt_xm.di.contract.DetailContract;
import com.example.tt_xm.di.contract.SyncContract;
import com.example.tt_xm.di.presenter.DetailPresenter;
import com.example.tt_xm.di.presenter.SyncPresenterImpl;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailContract.DetailsView, SyncContract.SyncView {


    @BindView(R.id.par_image_back)
    ImageView parImageBack;
    @BindView(R.id.par_goods)
    TextView parGoods;
    @BindView(R.id.par_par)
    TextView parPar;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.layour1)
    LinearLayout layour1;
    @BindView(R.id.par_lin)
    LinearLayout parLin;
    @BindView(R.id.par_banner)
    Banner parBanner;
    @BindView(R.id.par_text_price)
    TextView parTextPrice;
    @BindView(R.id.par_text_num)
    TextView parTextNum;
    @BindView(R.id.par_text_name)
    TextView parTextName;
    @BindView(R.id.par_text_content)
    TextView parTextContent;
    @BindView(R.id.par_text_weight)
    TextView parTextWeight;
    @BindView(R.id.par_text_kg)
    TextView parTextKg;
    @BindView(R.id.par_text_qing)
    TextView parTextQing;
    @BindView(R.id.par_webview)
    WebView parWebview;
    @BindView(R.id.adds)
    ImageView adds;
    @BindView(R.id.par_image_buy)
    ImageView parImageBuy;
    private DetailContract.DetailsPresenter detailsPresenter;
    private ArrayList<String> imageurilist;
    private SyncContract.SyncPresenter syncPresenter;
    int userId;
    String sessionId;
    private Details.ResultBean resultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //沉浸式状态栏
        StatusBarUtil.setRootViewFitsSystemWindows(DetailsActivity.this,true);
        StatusBarUtil.setTranslucentStatus(DetailsActivity.this);
        if (!StatusBarUtil.setStatusBarDarkTheme(DetailsActivity.this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(DetailsActivity.this,0x55000000);
        }

        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        //接受cid
        imageurilist = new ArrayList<>();
        Intent intent = this.getIntent();
        int cid = intent.getIntExtra("cid", 0);
        Toast.makeText(DetailsActivity.this, cid + "", Toast.LENGTH_SHORT).show();



        //详情页面
        detailsPresenter = new DetailPresenter();
        detailsPresenter.attachView(this);
        detailsPresenter.requestData(cid,userId,sessionId);

        //同步购物车
        syncPresenter = new SyncPresenterImpl();
        syncPresenter.attachView(this);

        //添加到购物车
        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId1 = resultBean.getCommodityId();
                int count = 3;
                syncPresenter.requestData(commodityId1, count);
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
                String[] split = resultBean.getPicture().split("\\,");
                //将图片集合添加到图片的集合
                for (int i = 0; i < split.length; i++) {
                    imageurilist.add(split[i]);
                }
                parBanner.setImageLoader(new ImageLoder());
                parBanner.setImages(imageurilist);
                parBanner.start();

                //webView网页请求
                String detailes = resultBean.getDetails();
                WebSettings settings = parWebview.getSettings();
                settings.setJavaScriptEnabled(true);
                String js = "<script type=\"text/javascript\">" +

                        "var imgs=document.getElementsByTagName('img');" +
                        "for(var i = 0; i<imgs.length; i++){" +
                        "imgs[i].style.width='100%';" +
                        "imgs[i].style.height='auto';" +
                        "}" +
                        "</script>";
                parWebview.loadDataWithBaseURL(null, detailes + js + "<html></body>", "text/html", "utf-8", null);
                //价格
                parTextPrice.setText("$" + resultBean.getPrice());
                //名字
                parTextName.setText(resultBean.getCommodityName());
                //介绍
                parTextContent.setText(resultBean.getDescribe());
                //重量
                parTextKg.setText(resultBean.getWeight() + "kg");
                //销售数量
                parTextNum.setText("已售" + resultBean.getCommentNum() + "件");
            }
        });

    }

    @Override
    public void showDatas(String message) {
        Gson gson = new Gson();
        Sync sync = gson.fromJson(message, Sync.class);
        String status = sync.getStatus();
        if (status.equals("0000")) {
            Toast.makeText(DetailsActivity.this, "同步成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DetailsActivity.this, "同步失败", Toast.LENGTH_SHORT).show();
        }
    }
}
