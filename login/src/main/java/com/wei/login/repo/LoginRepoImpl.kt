package com.wei.login.repo

import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.model.SingleLiveData
import com.wei.common.network.support.serverData
import com.wei.login.net.LoginRsp
import com.wei.login.net.LoginService
import com.wei.service.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

/**
 * @ClassName LoginRepoImpl
 * @Author Rookie Wai
 * @Date 2021/8/6 15:28
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 登录模块相关数据的具体处理
 */
class LoginRepoImpl(private val loginService: LoginService) : LoginRepo {

    private val _loginRsp=SingleLiveData<LoginRsp>()

    override val loginRsp: LiveData<LoginRsp?> = _loginRsp

    override suspend fun requestLogin(password: String, username: String) {
        loginService.login(password, username)
            .serverData()
            .onSuccess {
                onBizOK<LoginRsp> { code, data, message ->
                    _loginRsp.value=data
                    LogUtils.i("登录接口 BizOK $data")
                }
                onBizError { code, message ->
                    LogUtils.w("登录接口 BizError $code,$message")
                    ToastUtils.showShort("$message")
                }
            }
            .onFailure {
                LogUtils.e("登录接口 异常 ${it.message}")
            }

    }

}
