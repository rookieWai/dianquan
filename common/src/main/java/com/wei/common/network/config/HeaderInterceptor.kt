package com.wei.common.network.config

import com.blankj.utilcode.util.EncryptUtils
import com.wei.common.network.utils.MySpUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @ClassName HeaderInterceptor
 * @Author Rookie Wai
 * @Date 2021/7/26 12:45
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class HeaderInterceptor :Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest=chain.request()

        val newRequest=originRequest.newBuilder()
            .cacheControl(CacheControl.FORCE_NETWORK)

        //如果有token加入请求参数中
        val localToken =
            MySpUtils.getString(SP_KEY_USER_TOKEN, originRequest.header("token")) ?: ""
        if (localToken.isNotEmpty()) {
            newRequest.header("Authorization",localToken)
        }

        return chain.proceed(newRequest.build())

    }


}