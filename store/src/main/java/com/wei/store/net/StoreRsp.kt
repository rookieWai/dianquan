package com.wei.store.net

import androidx.annotation.Keep

/**
 * @ClassName StoreTabRsp
 * @Author Rookie Wai
 * @Date 2021/8/13 20:14
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */


@Keep
class StoreTabRsp : ArrayList<StoreTabRsp.StoreTabRspItem>() {
    data class StoreTabRspItem(
        val children: List<Any?>?,
        val icon: String?,
        val id: Int,
        val keywords: String?,
        val level: Int,
        val name: String?,
        val navStatus: Int,
        val parentId: Int,
        val productCount: Int,
        val productUnit: String?,
        val showStatus: Int,
        val sort: Int
    )
}