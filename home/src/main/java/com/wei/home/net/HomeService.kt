package com.wei.home.net

import com.wei.service.network.BaseDQRsp
import retrofit2.Call
import retrofit2.http.GET

/**
 * @ClassName HomeService
 * @Author Rookie Wai
 * @Date 2021/8/20 19:54
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
interface HomeService {

    @GET("home/content")
    fun getHomeContent():Call<BaseDQRsp>

}