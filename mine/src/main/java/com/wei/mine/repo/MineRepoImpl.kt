package com.wei.mine.repo

import android.media.MediaRouter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.wei.common.network.support.serverData
import com.wei.mine.net.MineService
import com.wei.mine.net.UserInfoRsp
import com.wei.service.network.onBizError
import com.wei.service.network.onBizOK
import com.wei.service.network.onSuccess

/**
 * @ClassName MineRepoImpl
 * @Author Rookie Wai
 * @Date 2021/8/11 16:42
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 *mine模块相关数据的具体处理
 */
class MineRepoImpl(private val service:MineService): MineRepo{

    private val _userInfoRsp=MutableLiveData<UserInfoRsp>()

    override val liveUserInfo: LiveData<UserInfoRsp> = _userInfoRsp

    override suspend fun getUserInfo() {
        service.getUserInfo()
            .serverData()
            .onSuccess {
                onBizOK <UserInfoRsp>{ code, data, message ->
                    _userInfoRsp.value=data
                    LogUtils.i("获取用户信息成功 BizOK $data")
                    return@onBizOK
                }
                onBizError { code, message ->
                    _userInfoRsp.value=null
                    LogUtils.w("获取用户信息失败 BizError $code,$message")
                    return@onBizError
                }
            }

    }

}