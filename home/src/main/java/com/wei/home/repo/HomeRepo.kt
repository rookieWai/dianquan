package com.wei.home.repo

import androidx.lifecycle.LiveData
import com.wei.home.net.HomeContentRsp

/**
 * @ClassName HomeRepo
 * @Author Rookie Wai
 * @Date 2021/8/20 20:03
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
interface HomeRepo {
    //获取首页展示内容成功后返回的数据
    val homeContentRsp: LiveData<HomeContentRsp>

    suspend fun getHoneContent()
}