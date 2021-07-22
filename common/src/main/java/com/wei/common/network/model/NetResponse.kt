package com.wei.common.network.model

/**
 * @ClassName NetResponse
 * @Author Rookie Wai
 * @Date 2021/7/21 18:36
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 * 基础网络返回数据结构
 */
data class NetResponse(
    val code:Int, //响应码
    val data:Any?,//响应数据内容
    val message:String //响应数据的描述结果
)
