package com.wei.common.network.support

/**
 * @ClassName IHttpCallback
 * @Author Rookie Wai
 * @Date 2021/7/18 14:50
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 网络回调
 */
interface IHttpCallback {

    /**
     * 网络请求成功的回调
     * [data] 返回回调的数据
     */
    fun onSuccess(data:Any?)

    /**
     * 网络请求失败的回调
     * [error] 返回失败的信息
     */
    fun onFailed(error:Any?)

}