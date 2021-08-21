package com.wei.home.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.wei.common.network.support.serverData
import com.wei.home.net.HomeContentRsp
import com.wei.home.net.HomeService
import com.wei.service.network.onBizError
import com.wei.service.network.onBizOK
import com.wei.service.network.onFailure
import com.wei.service.network.onSuccess

/**
 * @ClassName HomeRepoImpl
 * @Author Rookie Wai
 * @Date 2021/8/20 20:03
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class HomeRepoImpl(private val homeService: HomeService) : HomeRepo {

    private val _homeContentRsp=MutableLiveData<HomeContentRsp>()

    override val homeContentRsp: LiveData<HomeContentRsp> = _homeContentRsp


    override suspend fun getHoneContent() {
        homeService.getHomeContent()
            .serverData()
            .onSuccess {
                onBizOK<HomeContentRsp> { code, data, message ->
                    _homeContentRsp.value=data
                    LogUtils.i("首页展示信息接口 BizOK $data")
                }
                onBizError { code, message ->
                    LogUtils.w("首页展示信息接口  onBizError $code $message")
                }
            }
            .onFailure {
                LogUtils.e("首页展示信息接口  ${it.message}")
            }
    }

}