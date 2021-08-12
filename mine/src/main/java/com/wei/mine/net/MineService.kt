package com.wei.mine.net


import com.wei.service.network.BaseDQRsp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * @ClassName MineService
 * @Author Rookie Wai
 * @Date 2021/8/11 16:32
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

interface MineService {

    @GET("sso/info")
    fun getUserInfo(): Call<BaseDQRsp>

}