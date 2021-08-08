package com.wei.login.repo

import androidx.lifecycle.LiveData
import com.wei.common.model.SingleLiveData
import com.wei.login.net.LoginRsp

/**
 * @ClassName LoginRepo
 * @Author Rookie Wai
 * @Date 2021/8/6 15:22
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 登录模块相关数据的接口
 */
interface LoginRepo {

    //登录成功后返回的结果
    val loginRsp: LiveData<LoginRsp?>

    //用于调用网络请求登录，返回登录结果
    suspend fun requestLogin(password:String,username:String)

}