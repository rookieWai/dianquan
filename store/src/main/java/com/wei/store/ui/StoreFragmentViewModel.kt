package com.wei.store.ui

import com.wei.common.base.BaseViewModel
import com.wei.store.repo.StoreRepo

/**
 * @ClassName StoreFragmentViewModel
 * @Author Rookie Wai
 * @Date 2021/8/13 20:47
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreFragmentViewModel(val repo: StoreRepo) : BaseViewModel() {

    val liveStoreTabRsp=repo.storeTabRsp

    fun getTab(){
        serverAwait {
            repo.getStoreTabRsp()
        }
    }

}