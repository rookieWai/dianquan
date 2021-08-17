package com.wei.store.ui.viewmodel

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
class ProductDetailActivityViewModel(private val repo:StoreRepo) : BaseViewModel() {

    var liveProductDetailRsp=repo.liveProductDetail

    fun getProductDetail(id:Int){
        serverAwait {
            repo.getProductDetail(id)
        }
    }
}