package com.wei.store.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.wei.common.base.BaseViewModel
import com.wei.store.repo.StoreRepo
import com.wei.store.ui.StoreCarActivity
import com.wei.store.ui.adapter.StoreProductListAdapter

/**
 * @ClassName StoreFragmentViewModel
 * @Author Rookie Wai
 * @Date 2021/8/13 20:47
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreFragmentViewModel(val repo: StoreRepo) : BaseViewModel() {

    val liveStoreTabRsp=repo.liveStoreTab

    val liveStoreProductRsp=repo.liveStoreProduct

    val adapter=StoreProductListAdapter

    //获取商品分类
    fun getTab(){
        serverAwait {
            repo.getStoreTabRsp()
        }
    }

    //根据类别id，获取商品列表
    suspend fun getProductListById(id: Int? =null)
    =repo.getStoreProduct(id).cachedIn(viewModelScope)


}