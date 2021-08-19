package com.wei.store.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.network.support.serverData
import com.wei.common.network.support.serverRsp
import com.wei.service.network.onBizError
import com.wei.service.network.onBizOK
import com.wei.service.network.onFailure
import com.wei.service.network.onSuccess
import com.wei.store.net.*
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
    private val _productDetailRsp=MutableLiveData<ProductDetailRsp>()
    private val _carListRsp=MutableLiveData<CarListRsp>()


    override val liveStoreTab: LiveData<StoreTabRsp> = _storeTabRsp
    override val liveStoreProduct: LiveData<StoreProductRsp> =  _storeProductRsp
    override val liveProductDetail: LiveData<ProductDetailRsp?> = _productDetailRsp
    override val liveCarList: LiveData<CarListRsp?> = _carListRsp

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


    override suspend fun getProductDetail(id: Int) {
        storeService.getProductDetailById(id)
            .serverData()
            .onSuccess {
                onBizOK<ProductDetailRsp> { code, data, message ->
                    _productDetailRsp.value=data
                    LogUtils.i("商品详情接口 BizOK $data")
                }
                onBizError { code, message ->
                    _productDetailRsp.value=null
                    LogUtils.w("商品详情接口 BizError $code $message" )
                }
            }
            .onFailure {
                LogUtils.e("商品详情接口异常 ${it.message}")
            }
    }

    override suspend fun addProductToCar(data: ProductToCarData) {
        storeService.addProductToCar(data)
            .serverData()
            .onSuccess {
                onBizOK<Int> { code, data, message ->
                    ToastUtils.showShort("添加成功")
                    LogUtils.i("添加商品到购物车接口 BizOK $data")
                }
                onBizError { code, message ->
                    ToastUtils.showShort("添加失败")
                    LogUtils.w("添加商品到购物车接口 BizError $code $message" )
                }
            }
            .onFailure {
                ToastUtils.showShort("添加失败")
                LogUtils.e("添加商品到购物车异常 ${it.message}")
            }
    }

    override suspend fun getCarList(callback: (dataList:ArrayList<CarListRsp.CarListRspItem>) -> Unit) {
        storeService.getCarList()
            .serverData()
            .onSuccess {
                onBizOK <CarListRsp>{ code, data, message ->
                    _carListRsp.value=data
                    if (data != null) {
                        callback.invoke(data)
                    }
                    LogUtils.i("购物车列表接口 BizOK $data")
                }
                onBizError { code, message ->
                    _productDetailRsp.value=null
                    LogUtils.w("购物车列表接口 BizError $code $message" )
                }
            }
            .onFailure {
                LogUtils.e("购物车列表接口异常 ${it.message}")
            }

    }

    override suspend fun updateCarProductQuantity(id: Int, quantity: Int, callback: () -> Unit) {
        storeService.updateCarProductQuantity(id,quantity)
            .serverData()
            .onSuccess {
                onBizOK<Int> { code, data, message ->
                    LogUtils.i("修改购物车商品数量接口 BizOK $data")
                    callback.invoke()  //用于更新列表的回调方法
                }
                onBizError { code, message ->
                    LogUtils.w("修改购物车商品数量接口 BizError $code $message" )
                    ToastUtils.setGravity(0,0,0)
                    ToastUtils.showShort("修改失败")
                }
            }
            .onFailure {
                LogUtils.e("修改购物车商品数量接口异常 ${it.message}")
            }
    }

    override suspend fun clearCar(callback: () -> Unit) {
        storeService.clearCar()
            .serverData()
            .onSuccess {
                onBizOK<Int> { code, data, message ->
                    LogUtils.i("清空购物车接口 BizOK $data")
                    callback.invoke()  //成功后，用于更新列表的回调方法
                }
                onBizError { code, message ->
                    LogUtils.w("清空购物车接口 BizError $code $message" )
                    ToastUtils.setGravity(0,0,0)
                    ToastUtils.showShort("清空失败")
                }
            }
            .onFailure {
                LogUtils.e("清空购物车接口 ${it.message}")
            }

    }

    override suspend fun deleteCarByListId(list: List<Int>, callback: () -> Unit) {
        storeService.deleteCarByListId(list)
            .serverData()
            .onSuccess {
                onBizOK<Int> { code, data, message ->
                    LogUtils.i("删除购物车某些商品接口 BizOK $data")
                    callback.invoke()  //成功后，用于更新列表的回调方法
                }
                onBizError { code, message ->
                    LogUtils.w("删除购物车某些商品接口 BizError $code $message" )
                    ToastUtils.setGravity(0,0,0)
                    ToastUtils.showShort("删除失败")
                }
            }
            .onFailure {
                LogUtils.e("删除购物车某些商品接口 ${it.message}")
            }
    }


}