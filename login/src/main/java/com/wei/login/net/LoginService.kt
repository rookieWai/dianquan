package com.wei.login.net

import com.wei.service.network.BaseDQRsp
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @ClassName LoginService
 * @Author Rookie Wai
 * @Date 2021/8/6 15:14
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 登录模块的接口
 */

interface LoginService {

    @POST("sso/login")
    fun login(@Query("password") password:String,
              @Query("username") username:String): Call<BaseDQRsp>

}