package com.wei.service.network

import androidx.annotation.Keep
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.google.gson.annotations.Until
import com.wei.common.network.model.DataResult
import com.wei.common.network.model.succeeded
import java.lang.annotation.ElementType
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @ClassName NetRspKtx
 * @Author Rookie Wai
 * @Date 2021/8/1 9:49
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

@Keep
data class BaseDQRsp(
    val code:Int, //响应码
    val data: Any?, //响应数据内容
    val message:String, //响应数据结果
)

/**
 * 这里表示网络请求成功，并得到业务服务器的响应
 * 将BaseDQRsp的data转化为响应的对象类型
 * @return 返回需要的类型对象，可能为null
 */



/**
 * 网络请求成功的回调
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(
    action:R.()->Unit
):DataResult<R>{
    // 告诉编译器：
    // “这个函数会在此时此处调⽤‘action’，并且最多只调⽤⼀次”
    contract {
        callsInPlace(action,InvocationKind.AT_MOST_ONCE)
    }
    if(succeeded) action.invoke((this as DataResult.Success<R>).data)
    return this
}

/**
 * 网络请求错误的回调
 */
@OptIn(ExperimentalContracts::class)
inline fun <R>DataResult<R>.onFailure(
    action:(exception:Throwable)->Unit
):DataResult<R>{
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Error) action.invoke(exception)
    return this
}


/**
 * 网络请求成功，code=200
 */
@OptIn(ExperimentalContracts::class)
inline  fun <reified T> BaseDQRsp.onBizOK
            (crossinline action:(code:Int,data:T?,message:String?)->Unit):BaseDQRsp{
    contract {
        callsInPlace(action,InvocationKind.EXACTLY_ONCE)
    }
    if(code==200){
        action.invoke(code, GsonUtils.fromJson(GsonUtils.toJson(data),T::class.java),message)
    }
    return this
}

/**
 * 网络请求成功，但是code!=200
 */
@OptIn(ExperimentalContracts::class)
inline fun BaseDQRsp.onBizError(crossinline block: (code: Int, message: String) -> Unit): BaseDQRsp {
    // 告诉编译器：
    // “这个函数会在此时此处调⽤‘block’，并且刚好只调⽤⼀次”
    contract {
        callsInPlace(block,InvocationKind.EXACTLY_ONCE)
    }
    if (code != 200) {
        block.invoke(code, message ?: "Error Message Null")
    }
    return this
}


