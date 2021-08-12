package com.wei.common.network

import com.wei.common.network.config.BASE_URL
import com.wei.common.network.config.HeaderInterceptor
import com.wei.common.network.config.LocalCookieJar
import com.wei.common.network.config.RetryInterceptor
import com.wei.common.network.support.LiveDataCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @ClassName Retrofit
 * @Author Rookie Wai
 * @Date 2021/7/20 14:33
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

object RetrofitApi {

    //重试次数
    private const val maxRetry=1;

    //获取okHttpClient
    private val mClient= OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS) //请求超时时间
        .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
        .readTimeout(10, TimeUnit.SECONDS) //读取数据超时时间
        .writeTimeout(10, TimeUnit.SECONDS) //写入数据超时时间
        .retryOnConnectionFailure(true) //重连
        .followRedirects(false) //重定向
        .cache(Cache(File("data/user/0/com.wei.dianquan/cache","okhttp"),1024)) //指定缓存数据放入的文件
        .cookieJar(LocalCookieJar())
        .addNetworkInterceptor(RetryInterceptor(maxRetry)) //添加重试网络拦截器，当前重试次数为1
        .addNetworkInterceptor(HeaderInterceptor())
        .build()

    //构建retrofitBuilder
    private val retrofitBuilder=Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create()) //json解析
        .addCallAdapterFactory(LiveDataCallAdapterFactory())  //返回数据的适配器
        .client(mClient)

    private var retrofit:Retrofit?=null

    /**
     * retrofit初始化配置
     *[baseUrl] 项目接口的基础url
     */
    fun initConfig(baseUrl:String=BASE_URL,okHttpClient: OkHttpClient= mClient):RetrofitApi{
        retrofit= retrofitBuilder.baseUrl(baseUrl).client(okHttpClient).build()
        return this
    }

    /**
     * 获取retrofit的service对象，动态代理对象
     * [serviceClazz] retrofit的service接口类
     */
    fun <T> getService(serviceClazz:Class<T>):T{
        if (retrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit未初始化，需要配置baseURL")
        } else {
            return this.retrofit!!.create(serviceClazz)
        }
    }

}