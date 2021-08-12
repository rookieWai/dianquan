package com.wei.common.network.config

import com.blankj.utilcode.util.EncryptUtils
import com.google.gson.reflect.TypeToken
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


        gToken.value?.let { newRequest.header("Authorization", it) }


        return chain.proceed(newRequest.build())

    }


}