package com.wei.common

import okhttp3.*

/**
 * @ClassName RetryInterceptor
 * @Author Rookie Wai
 * @Date 2021/7/18 15:06
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 重试请求的网络拦截器
 *
 * [maxRetry] 重试次数，默认为0
 */
class RetryInterceptor(private val maxRetry:Int=0) :Interceptor{

    //记录当前重试次数
    private var retriedNum:Int=0;

    override fun intercept(chain: Interceptor.Chain): Response {

        val request:Request=chain.request()
        //第一次执行请求
        var response=chain.proceed(request)
        //如果请求没有成功,开启重试
        while (!response.isSuccessful&&retriedNum<maxRetry){
            retriedNum++
            response=chain.proceed(request)
        }
        return response
    }

}