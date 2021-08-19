package com.wei.store.net

import com.wei.service.network.BaseDQRsp
import retrofit2.Call
import retrofit2.http.*

/**
 * @ClassName StoreService
 * @Author Rookie Wai
 * @Date 2021/8/13 20:16
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
interface StoreService {
    //获取商品分类
    @GET("product/categoryTreeList")
    fun getStoreTab(): Call<BaseDQRsp>

    //根据productCategoryId产品类别id获取商品
    @GET("product/search")
    fun getStoreProductByCategoryId(
        @Query("productCategoryId") productCategoryId: Int? =null,
        @Query("pageNum") pageNum:Int=1,  //页码，默认为1
        @Query("pageSize") pageSize:Int=10, //每页显示数，默认为10
        @Query("sort") sort:Int=0 //排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低
    ) : Call<BaseDQRsp>


    //根据商品id获取商品详情
    @GET("product/detail/{id}")
    fun getProductDetailById(@Path("id") id:Int):Call<BaseDQRsp>

    //添加商品到购物车
    @POST("cart/add")
    fun addProductToCar(@Body data:ProductToCarData):Call<BaseDQRsp>

    //获取购物车列表
    @GET("cart/list")
    fun getCarList():Call<BaseDQRsp>

    //修改购物车中某个商品的数量
    @GET("cart/update/quantity")
    fun updateCarProductQuantity(@Query("id") id:Int,@Query("quantity") quantity:Int):Call<BaseDQRsp>

    //清空购物车
    @POST("cart/clear")
    fun clearCar():Call<BaseDQRsp>

    //根据id列表删除购物车中商品
    @POST("cart/delete")
    fun deleteCarByListId(@Query("ids") ids:List<Int>):Call<BaseDQRsp>



}

