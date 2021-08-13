package com.wei.store.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.wei.common.network.support.serverData
import com.wei.service.network.onBizError
import com.wei.service.network.onBizOK
import com.wei.service.network.onFailure
import com.wei.service.network.onSuccess
import com.wei.store.net.StoreService
import com.wei.store.net.StoreTabRsp
import com.wei.store.repo.StoreRepo

/**
 * @ClassName StoreServiceLmpl
 * @Author Rookie Wai
 * @Date 2021/8/13 20:24
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreRepoImpl(private val storeService: StoreService): StoreRepo {

    private val _storeTabRsp=MutableLiveData<StoreTabRsp>()

    override val storeTabRsp: MutableLiveData<StoreTabRsp> = _storeTabRsp

    override suspend fun getStoreTabRsp() {
        super.getStoreTabRsp()
        storeService.getStoreTab()
            .serverData()
            .onSuccess {
                onBizOK<StoreTabRsp> { code, data, message ->
                    _storeTabRsp.value=data
                    LogUtils.i("商品分类接口 BizOK $data")
                }

                onBizError { code, message ->
                    LogUtils.w("商品分类接口 onBizError $code $message")
                }
            }
            .onFailure {
                LogUtils.e("商品分类接口异常 ${it.message}")
            }

    }

}