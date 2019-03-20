package com.example.tt_xm.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.Search;
import com.example.tt_xm.di.contract.SearchContract;
import com.example.tt_xm.di.presenter.SearchPresenter;
import com.example.tt_xm.ui.adapter.SerachAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchContract.SearchView{

    @BindView(R.id.image_Popwindow)
    ImageView imagePopwindow;
    @BindView(R.id.eDit_name)
    EditText eDitName;
    @BindView(R.id.image_souu)
    ImageView imageSouu;
    @BindView(R.id.image_shibai)
    ImageView imageShibai;
    @BindView(R.id.text_shi)
    TextView textShi;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    int page = 1;
    int count = 5;
    private String name;
    private SearchContract.SearchPresenter searchPresenter;
    private SmartRefreshLayout smart;
    private SerachAdapter serachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        smart = findViewById(R.id.smart);

        searchPresenter = new SearchPresenter();
        searchPresenter.attach(this);
        searchPresenter.requestData(name,page,count);

        /*smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                searchPresenter.requestData(name,page,count);
                refreshLayout.finishRefresh(2000);
            }
        });*/

        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                count+=5;
                searchPresenter.requestData(name,page,count);
                serachAdapter.notifyDataSetChanged();
                smart.finishLoadMore(true);
            }
        });

        imagePopwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageSouu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = eDitName.getText().toString().trim();
                if (name.equals("")){
                    return;
                }
                searchPresenter.requestData(name, page, count);
            }
        });
    }

    @Override
    public void searchData(Search search) {
        List<Search.ResultBean> beans = search.getResult();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        serachAdapter = new SerachAdapter();
        serachAdapter.setData(SearchActivity.this,beans);
        recyclerview.setAdapter(serachAdapter);
        serachAdapter.notifyDataSetChanged();
        if (beans.size()==0){
            imageShibai.setVisibility(View.VISIBLE);
            textShi.setVisibility(View.VISIBLE);
        }else {
            imageShibai.setVisibility(View.GONE);
            textShi.setVisibility(View.GONE);
            serachAdapter.notifyDataSetChanged();
        }
    }
}
