package com.wei.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseViewModel
import com.wei.login.repo.LoginRepo

/**
 * @ClassName LoginActivityViewModel
 * @Author Rookie Wai
 * @Date 2021/7/25 21:14
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

class LoginActivityViewModel(private val repo:LoginRepo) :BaseViewModel(){

    var username=ObservableField<String>()
    var password=ObservableField<String>()

    val liveLoginRsp=repo.loginRsp

    //登录
    internal fun repoLogin(){
        val username=username.get()?:return
        val password=password.get()?:return
        serverAwait {
            repo.requestLogin(password,username)
        }
    }



    fun goLogin(){
        repoLogin()
    }

    //region 未实现
    fun wechat(ctx: Context) {
        ToastUtils.showShort("点击了微信登录，未实现")
    }

    fun qq() {
        ToastUtils.showShort("点击了QQ登录，未实现")
    }

    fun alipay() {
        ToastUtils.showShort("点击了支付宝登录，未实现")
    }

    fun phoneLogin(view: View){
        ToastUtils.showShort("未实现")
    }



    //endregion
}