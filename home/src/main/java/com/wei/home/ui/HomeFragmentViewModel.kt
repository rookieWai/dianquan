package com.wei.home.ui

import com.wei.common.base.BaseViewModel
import com.wei.home.repo.HomeRepo

/**
 * @ClassName HomeFragmentViewModel
 * @Author Rookie Wai
 * @Date 2021/8/20 20:17
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class HomeFragmentViewModel(private val repo:HomeRepo) : BaseViewModel() {

    val liveHomeContent=repo.homeContentRsp

    fun getHomeContent(){
        serverAwait {
            repo.getHoneContent()
        }
    }
}