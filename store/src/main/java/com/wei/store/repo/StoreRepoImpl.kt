package com.wei.store.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.wei.common.network.support.serverData
import com.wei.service.network.onBizError
import com.wei.service.network.onBizOK
import com.wei.service.network.onFailure
import com.wei.service.network.onSuccess
import com.wei.store.net.StoreProductRsp
import com.wei.store.net.StoreService
import com.wei.store.net.StoreTabRsp
import com.wei.store.repo.pagingsource.StoreProductListPagingSource
import kotlinx.coroutines.flow.Flow

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
    private val _storeProductRsp=MutableLiveData<StoreProductRsp>()

    override val storeTabRsp: LiveData<StoreTabRsp> = _storeTabRsp
    override val storeProductRsp: LiveData<StoreProductRsp> =  _storeProductRsp

    //商品分类
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


    private val pageSize = 10

    /**
     *根据id获取商品
     *使用分页数据容器PagingData装载PagingDataSource,并加载配置
     *创建PagingData数据流，需要创建一个 Pager 实例，并提供一个 PagingConfig
     *配置对象和一个可以告诉Pager如何获取您实现的 PagerSource 的实例的函数，以供Pager使用
     */
    override suspend fun getStoreProduct(
        productCategoryId: Int?,    //课程id
    ): Flow<PagingData<StoreProductRsp.Data>> {
        val pagingConfig = PagingConfig(
            pageSize = pageSize, // 每页显示的数据的大小。对应 PagingSource 里 LoadParams.loadSize
            prefetchDistance = 2, // 预刷新的距离，距离最后一个 item 多远时加载数据，默认为 pageSize
            initialLoadSize = 10,  // 初始化加载数量，默认为 pageSize * 3
            maxSize = pageSize * 3 // 一次应在内存中保存的最大数据，默认为 Int.MAX_VALUE
        )
        return Pager(config = pagingConfig, null) {
            StoreProductListPagingSource(storeService,productCategoryId)
        }.flow
    }

}