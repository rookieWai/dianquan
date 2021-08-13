package com.wei.store.repo

import androidx.lifecycle.LiveData
import com.wei.store.net.StoreTabRsp

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

    //用于调用获取商品分类的接口
    suspend fun getStoreTabRsp(){}

}
