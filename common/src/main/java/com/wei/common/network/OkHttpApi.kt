package com.wei.common.network

import androidx.collection.SimpleArrayMap
import com.google.gson.Gson
import com.wei.common.network.config.LocalCookieJar
import com.wei.common.network.config.RetryInterceptor
import com.wei.common.network.support.IHttpCallback
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * @ClassName OkHttpApi
 * @Author Rookie Wai
 * @Date 2021/7/18 14:56
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

class OkHttpApi: IHttp {

    private var baseUrl="http://39.105.140.1:8085/"

    //重试次数
    private val maxRetry=0;

    //存储请求，用于取消
    private val callMap=SimpleArrayMap<Any,Call>()

    //获取okHttpClient
    private val defaultClient=OkHttpClient.Builder()
        .callTimeout(10,TimeUnit.SECONDS) //请求超时时间
        .connectTimeout(10,TimeUnit.SECONDS) //连接超时时间
        .readTimeout(10,TimeUnit.SECONDS) //读取数据超时时间
        .writeTimeout(10,TimeUnit.SECONDS) //写入数据超时时间
        .retryOnConnectionFailure(true) //重连
        .followRedirects(true) //重定向
        .cache(Cache(File("sdcard/cache","okhttp"),1024)) //指定缓存数据放入的文件
        .addNetworkInterceptor(RetryInterceptor(maxRetry)) //添加重试网络拦截器，当前重试次数为1
        .cookieJar(LocalCookieJar())
        .build()

    private var mClient=defaultClient

    fun getClient()=mClient

    /**
     * 配置自定义的client
     */
    fun initConfig(client :OkHttpClient){
        this.mClient=client
    }

    companion object{
        @Volatile
        private var api:OkHttpApi?=null

        @Synchronized
        fun getInstance():OkHttpApi{
            return api?:OkHttpApi().also { api=it }
        }
    }

    override fun get(params: Map<String, Any>?, path: String, callback: IHttpCallback) {

        val url="$baseUrl$path"
        //将url与传入的参数做拼接
        val urlBuilder=url.toHttpUrl().newBuilder()
        params?.forEach{entry ->
            urlBuilder.addEncodedQueryParameter(entry.key,entry.value.toString())
        }

        val request:Request=Request.Builder()
            .get()
            .tag(params)  //标签
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK) //缓存，仅仅使用网络
            .build()

        //获取call对象
        val mCall=mClient.newCall(request)
        //将call对象存入callMap，用于取消请求
        callMap.put(request.tag(),mCall)

        mCall.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message?:"")
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response.body?.string()?:"")
            }

        });


    }

    override fun post(body: Any, path: String, callback: IHttpCallback) {
        val request = Request.Builder()
            .post(Gson().toJson(body).toRequestBody())
            .url(baseUrl)
            .tag(body)
            .build()


        val mCall=mClient.newCall(request)
        callMap.put(request.tag(),mCall)

        mCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response.body?.string()?:"")
            }

        })
    }

    override fun cancelRequest(tag: Any) {
        callMap.get(tag)?.cancel()
    }

    override fun cancelAllRequest() {
        for(i in 0 until callMap.size()){
            callMap.get(callMap.keyAt(i))?.cancel()
        }
    }

    //使用协程实现get请求
    fun get(params: Map<String, Any>?,path:String )= runBlocking {
        val urlBuilder=path.toHttpUrl().newBuilder()
        params?.forEach{entry ->
            urlBuilder.addEncodedQueryParameter(entry.key,entry.value.toString())
        }

        val request=Request.Builder()
            .get()
            .tag(params)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        val newCall=mClient.newCall(request)
        callMap.put(request.tag(),newCall)
        newCall.call()
    }

    //自定义扩展函数，扩展okhttp的call的异步执行方式，结合coroutines，返回DataResult的数据响应
    private suspend fun Call.call(async: Boolean = true): Response {
        return suspendCancellableCoroutine {
            if (async) {
                enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        if (it.isCancelled) return
                        it.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        it.resume(response)
                    }
                })
            } else {
                it.resume(execute())
            }
            it.invokeOnCancellation {
                try {
                    cancel()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

}