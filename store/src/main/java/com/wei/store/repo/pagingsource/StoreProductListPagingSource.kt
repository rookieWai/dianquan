package com.wei.store.repo.pagingsource

import androidx.paging.PagingSource
import com.blankj.utilcode.util.LogUtils
import com.wei.common.network.support.serverData
import com.wei.service.network.onBizOK
import com.wei.service.network.onFailure
import com.wei.service.network.onSuccess
import com.wei.store.net.StoreProductRsp
import com.wei.store.net.StoreService
import java.lang.Exception

/**
 * @ClassName StoreProductListPagingSource
 * @Author Rookie Wai
 * @Date 2021/8/14 15:27
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 *通过商品类别id获取商品的分页数据源
 *请求数据并处理
 */

class StoreProductListPagingSource(
    private val service:StoreService,
    private val productCategoryId: Int? =null
):PagingSource<Int,StoreProductRsp.Data>(){

    //请求操作就在load方法中完成
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreProductRsp.Data> {
        var result: LoadResult<Int, StoreProductRsp.Data> =
            LoadResult.Error(Exception("加载中"))

        //获取页码，页码未定义置为1
        var pageNum  = params.key ?: 1
        service.getStoreProductByCategoryId(productCategoryId,pageNum)
            .serverData()
            .onSuccess {
                onBizOK<StoreProductRsp> { code, data, message ->
                    LogUtils.i("获取商品列表Paging3 BizOK $data")
                    val totalPage = data?.totalPage ?: 0
                    result = LoadResult.Page(
                        //需要加载的数据
                        data = data?.datas ?: emptyList(),
                        //初始化的时候要为null，避免第一页重复加载，仅向后翻页，如果可以往上加载更多就设置该参数，否则不设置
                        prevKey = null,
                        //加载下一页的key 如果传null就说明到底了
                        nextKey = if (pageNum< totalPage) pageNum.plus(1) else null,
                    )
                }
            }
            .onFailure {
                LogUtils.e("获取课程列表Paging3 接口异常 ${it.message}")
                result = LoadResult.Error(it)
            }

        return result
    }

}