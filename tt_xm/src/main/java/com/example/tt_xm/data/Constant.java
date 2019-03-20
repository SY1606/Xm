package com.example.tt_xm.data;

import android.arch.core.util.Function;

import java.io.FileInputStream;

public class Constant {
    //登录
    public static final String Login = "http://172.17.8.100/small/user/v1/login";
    //注册
    public static final String Zhuce = "http://172.17.8.100/small/user/v1/register";

    //商品
    public static final String Goods = "http://172.17.8.100/small/commodity/v1/commodityList";

    //商品详情
    //详情
    public static final String Xq = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=";

    //查询商品
    public static final String Searchs= "http://172.17.8.100/";

    //圈子列表
    public static final String Ufo ="http://172.17.8.100/";

    //轮播图
    public final static String Banner = "http://172.17.8.100/";

    //同步购物车
    public static final String Sync = "http://172.17.8.100/";

    //查询购物车
    public static final String ShopCar ="http://172.17.8.100/";
}
