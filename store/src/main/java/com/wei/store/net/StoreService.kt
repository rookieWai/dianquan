package com.wei.store.net

import com.wei.service.network.BaseDQRsp
import retrofit2.http.GET

/**
 * @ClassName StoreService
 * @Author Rookie Wai
 * @Date 2021/8/13 20:16
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
interface StoreService {
    @GET("product/categoryTreeList")
    fun getStoreTab(): retrofit2.Call<BaseDQRsp>
}