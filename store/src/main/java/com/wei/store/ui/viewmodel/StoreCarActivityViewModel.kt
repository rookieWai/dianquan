package com.wei.store.ui.viewmodel

import com.wei.common.base.BaseViewModel
import com.wei.store.repo.StoreRepo

/**
 * @ClassName ProductCarActiviyViewModel
 * @Author Rookie Wai
 * @Date 2021/8/16 22:56
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreCarActivityViewModel(private val repo: StoreRepo) : BaseViewModel() {

    val liveCarList=repo.liveCarList

    fun getCarList(){
        serverAwait {
            repo.getCarList()
        }
    }

}