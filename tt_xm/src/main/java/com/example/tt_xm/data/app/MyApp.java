package com.example.tt_xm.data.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.example.tt_xm.R;
import com.example.tt_xm.data.net.ScreenUtil;
import com.example.tt_xm.data.net.StatusBarUtil;
import com.example.tt_xm.greenDao.DaoMaster;
import com.example.tt_xm.greenDao.DaoSession;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


public class MyApp extends Application {
    private DaoMaster daoMaster;
    private static DaoSession daoSession;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;

    //smartrefreshLayout
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();


        ScreenUtil.resetDensity(this);
        mHelper = new DaoMaster.DevOpenHelper(MyApp.this, "jia",null);
        db = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        fresco();
    }

    private void fresco() {
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(this);
        //对已解码的图片进行缓存
        builder.setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                int maxMemory = (int) Runtime.getRuntime().maxMemory();
                int squareMemory = maxMemory / 8;
                MemoryCacheParams memoryCacheParams = new MemoryCacheParams(squareMemory
                        , Integer.MAX_VALUE
                        , squareMemory
                        , Integer.MAX_VALUE
                        , Integer.MAX_VALUE
                );
                return memoryCacheParams;
            }
        });
        ImagePipelineConfig config = builder.build();
        //在初始化中配置
        Fresco.initialize(this, config);
    }
    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
