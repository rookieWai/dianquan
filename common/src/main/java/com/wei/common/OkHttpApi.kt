package com.wei.common

import com.google.gson.Gson
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @ClassName OkHttpApi
 * @Author Rookie Wai
 * @Date 2021/7/18 14:56
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

class OkHttpApi:IHttp{

    private var baseUrl="http://39.105.140.1:8085/"

    //获取okHttpClient
    private val mClient=OkHttpClient.Builder()
        .callTimeout(10,TimeUnit.SECONDS) //请求超时时间
        .connectTimeout(10,TimeUnit.SECONDS) //连接超时时间
        .readTimeout(10,TimeUnit.SECONDS) //读取数据超时时间
        .writeTimeout(10,TimeUnit.SECONDS) //写入数据超时时间
        .retryOnConnectionFailure(true) //重连
        .followRedirects(false) //重定向
        .cache(Cache(File("sdcard/cache","okhttp"),1024)) //指定缓存数据放入的文件
        .addNetworkInterceptor(RetryInterceptor(1)) //添加重试网络拦截器，当前重试次数为1
        .build()


    override fun get(params: Map<String, Any>?, path: String, callback: IHttpCallback) {

        val url="$baseUrl$path"

        val urlBuilder=url.toHttpUrl().newBuilder()
        params?.forEach{entry ->
            urlBuilder.addEncodedQueryParameter(entry.key,entry.value.toString())
        }

        val request:Request=Request.Builder()
            .get()
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK) //缓存，仅仅使用网络
            .build()

        mClient.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response)
            }

        });


    }

    override fun post(body: Any, path: String, callback: IHttpCallback) {
        val request = Request.Builder()
            .post(Gson().toJson(body).toRequestBody())
            .url("http://39.105.140.1:8085/sso/login")
            .tag(body)
            .build()

        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response)
            }

        })
    }

}