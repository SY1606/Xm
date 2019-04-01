package com.example.tt_xm.data;

import com.example.tt_xm.data.bean.Details;
import com.example.tt_xm.data.bean.Goods;
import com.example.tt_xm.data.bean.Search;
import com.example.tt_xm.data.bean.Sync;
import com.example.tt_xm.data.bean.Ufo;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    //搜索
    @GET("small/commodity/v1/findCommodityByKeyword")
     Observable<Search> getSearchData(@Query("keyword") String name, @Query("page") int page, @Query("count") int count);

    //圈子
    @GET("small/circle/v1/findCircleList")
    Observable<Ufo> getUFOContent(@Header("sessionId") String sessionId, @Header("userId") int userId,@Query("page") int page, @Query("count") int count);

    //详情
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<ResponseBody> getDetalisContent(@Header("sessionId") String sessionId,
                                          @Header("userId") Integer userId,
                                          @Query("commodityId") Integer cmid);

    //轮播图
    @GET("small/commodity/v1/bannerShow")
    Call<ResponseBody> getbannerdata();

    //同步购物车
    @FormUrlEncoded
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<ResponseBody> getSyncContent(@Header("userId")int userId,
                                            @Header("sessionId")String sessionId,
                                            @Field("data")String data);

    //查询购物车
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ResponseBody> getShopCarContent(@Header("userId")int userId,
                                               @Header("sessionId")String sessionId);

    //我的圈子
    @GET("small/circle/verify/v1/findMyCircleById")
    Observable<ResponseBody> getMyCircleContent(@Header("userId")int userId,
                                              @Header("sessionId")String sessionId,
                                              @Query("page") int page,
                                              @Query("count") int count
                                              );

    //我的足迹
    @GET("small/commodity/verify/v1/browseList")
    Observable<ResponseBody> getMyTracks(@Header("userId")int userId,
                                                @Header("sessionId")String sessionId,
                                                @Query("page") int page,
                                                @Query("count") int count
    );

    //新增收货地址
    @FormUrlEncoded
    @POST("small/user/verify/v1/addReceiveAddress")
     Observable<ResponseBody> getNewAddresData(@Header("sessionId") String sessionId,
                                               @Header("userId") int userId,
                                               @Field("realName") String name,
                                               @Field("phone") String phone,
                                               @Field("address") String address,
                                               @Field("zipCode") String zipCode);

    //收获地址
    @GET("small/user/verify/v1/receiveAddressList")
    Observable<ResponseBody> getAddressContent(@Header("sessionId") String sessionId, @Header("userId") int userId);


    //我的钱包
    @GET("small/user/verify/v1/findUserWallet")
    Observable<ResponseBody> getWalletContent(@Header("userId")int userId,
                                              @Header("sessionId")String sessionId,
                                              @Query("page") Integer page,
                                              @Query("count") Integer count);


    //设置默认地址
    @POST("small/user/verify/v1/setDefaultReceiveAddress")
    Observable<ResponseBody> getDefaultContent(@Header("userId")Integer userId,
                                              @Header("sessionId")String sessionId,
                                              @Query("id")Integer id);



    //个人信息
    @GET("small/user/verify/v1/getUserById")
    Observable<ResponseBody> getPersonContent(@Header("userId")int userId,
                                           @Header("sessionId")String sessionId);

    //发布圈子
    @Multipart
    @POST("small/circle/verify/v1/releaseCircle?")
    Observable<ResponseBody> getSendContent(@Header("userId")Integer userId,
                                                 @Header("sessionId")String sessionId,
                                                 @Query("commodityId") int commodityId,
                                                 @Query("content") String content,
                                                 @Part MultipartBody.Part image);


    //创建订单

    @FormUrlEncoded
    @POST("small/order/verify/v1/createOrder")
    Observable<ResponseBody> getCorderContent(@Header("userId")Integer userId,
                                              @Header("sessionId")String sessionId,
                                              @FieldMap HashMap<String,String> map
    );

    //查询订单
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<ResponseBody> getQuorderContent(@Header("userId")Integer userId,
                                               @Header("sessionId")String sessionId,
                                               @Query("status")Integer status,
                                               @Query("page")Integer page,
                                               @Query("count")Integer count);

    //删除订单
    @DELETE("small/order/verify/v1/deleteOrder")
    Observable<ResponseBody> getDeleteOrderContent(@Header("userId")Integer userId,
                                                   @Header("sessionId")String sessionId,
                                                   @Query("orderId") String orderId);

    //支付
    @FormUrlEncoded
    @POST("small/order/verify/v1/pay?")
    Observable<ResponseBody> getPayContent(@Header("userId")Integer userId,
                                        @Header("sessionId")String sessionId,
                                        @Field("orderId")String orderId,
                                        @Field("payType") int payType
    );

    //根据订单状态查询订单信息
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<ResponseBody> getAllOrderData(@Header("userId")Integer userId,
                                             @Header("sessionId")String sessionId,
                                             @Query("status")int status,
                                             @Query("page")int page,
                                             @Query("count")int count);

    //收货
    @FormUrlEncoded
    @PUT("small/order/verify/v1/confirmReceipt")
    Observable<ResponseBody> getHarvestContent(@Header("userId")Integer userId,
                                           @Header("sessionId")String sessionId,
                                           @Field("orderId") String orderId);
}
