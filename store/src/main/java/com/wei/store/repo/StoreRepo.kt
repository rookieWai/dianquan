package com.wei.store.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.wei.store.net.StoreProductRsp
import com.wei.store.net.StoreTabRsp
import kotlinx.coroutines.flow.Flow

/**
 * @ClassName StoreRepo
 * @Author Rookie Wai
 * @Date 2021/8/13 20:19
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
interface StoreRepo {
    //返回商品分类数据
    val storeTabRsp:LiveData<StoreTabRsp>
    //商品数据
    val storeProductRsp:LiveData<StoreProductRsp>

    //用于调用获取商品分类的接口
    suspend fun getStoreTabRsp(){}

    //根据商品id获取商品列表
    suspend fun getStoreProduct(
        productCategoryId:Int?,
    ) :Flow<PagingData<StoreProductRsp.Data>>

}
