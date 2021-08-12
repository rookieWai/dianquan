package com.wei.mine.repo

import androidx.lifecycle.LiveData
import com.wei.mine.net.UserInfoRsp

/**
 * @ClassName MineRepo
 * @Author Rookie Wai
 * @Date 2021/8/11 16:41
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * mine模块相关数据的接口
 */
interface MineRepo {
    /**
     * 用户信息的返回数据类
     */
    val liveUserInfo: LiveData<UserInfoRsp>

    /**
     * 用于调用获取信息接口，返回用户信息
     */
    suspend fun getUserInfo()

}