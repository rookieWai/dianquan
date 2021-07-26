package com.wei.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ToastUtils

/**
 * @ClassName LoginActivityViewModel
 * @Author Rookie Wai
 * @Date 2021/7/25 21:14
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

class LoginActivityViewModel :ViewModel(){

    var username=ObservableField<String>()
    var password=ObservableField<String>()





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