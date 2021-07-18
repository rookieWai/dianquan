package com.wei.common

/**
 * @ClassName IHttp
 * @Author Rookie Wai
 * @Date 2021/7/18 14:46
 *
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */


/**
 * 网络请求的统一公共接口
 */
interface IHttp {

    //http的异步get请求
    fun get(params: Map<String, Any>?, path:String, callback:IHttpCallback)

    //http的异步post请求
    fun post(body:Any,path:String,callback: IHttpCallback)

}