package com.example.tt_xm.data;

import com.example.tt_xm.data.bean.Goods;
import com.example.tt_xm.data.bean.Search;
import com.example.tt_xm.data.bean.Sync;
import com.example.tt_xm.data.bean.Ufo;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
    //搜索
    @GET("small/commodity/v1/findCommodityByKeyword")
     Observable<Search> getSearchData(@Query("keyword") String name, @Query("page") int page, @Query("count") int count);

    //圈子
    @GET("small/circle/v1/findCircleList")
    Observable<Ufo> getUFOContent(@Header("sessionId") String sessionId, @Header("userId") int userId,@Query("page") int page, @Query("count") int count);

    //轮播图
    @GET("small/commodity/v1/bannerShow")
    Call<ResponseBody> getbannerdata();

    //同步购物车
    @FormUrlEncoded
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<ResponseBody> getSyncContent(
                                            @Field("data")String data);

    //查询购物车
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ResponseBody> getShopCarContent();
}
