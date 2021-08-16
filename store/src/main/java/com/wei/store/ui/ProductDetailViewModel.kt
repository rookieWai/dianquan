package com.wei.store.ui

import androidx.lifecycle.MutableLiveData
import com.wei.common.base.BaseViewModel
import com.wei.store.repo.StoreRepo

/**
 * @ClassName ProductDetailViewModel
 * @Author Rookie Wai
 * @Date 2021/8/15 20:33
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class ProductDetailViewModel(private val repo:StoreRepo) : BaseViewModel() {

    val liveProductDetailRsp=repo.liveProductDetail

    fun getProductDetail(id:Int){
        serverAwait {
            repo.getProductDetail(id)
        }
    }
}