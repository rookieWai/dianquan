package com.wei.mine.ui

import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseViewModel
import com.wei.mine.net.UserInfoRsp
import com.wei.mine.repo.MineRepo

/**
 * @ClassName MineViewModel
 * @Author Rookie Wai
 * @Date 2021/8/11 16:55
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MineFragmentViewModel(private val repo:MineRepo) :BaseViewModel(){

    val liveInfo  = repo.liveUserInfo

    /**
     * 获取用户信息
     */
    fun getUserInfo(){
        serverAwait {
            repo.getUserInfo()
        }
    }


    fun setting(){
        ToastUtils.showShort("点击了设置，还未实现")
    }

    fun message(){
        ToastUtils.showShort("点击了消息，还未实现")
    }



}