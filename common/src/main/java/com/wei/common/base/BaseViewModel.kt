package com.wei.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @ClassName BaseViewModel
 * @Author Rookie Wai
 * @Date 2021/7/26 14:01
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

open class BaseViewModel : ViewModel() {

    //jobs列表 job异步任务
    private val jobs= mutableListOf<Job>()
    val isLoading=MutableLiveData<Boolean>()  //表示网络请求的状态

    /**
     * 使用协程进行网络请求
     */
    protected fun serverAwait(block:suspend CoroutineScope.()->Unit)=viewModelScope.launch {
        isLoading.value=true
        block.invoke(this)
        isLoading.value=false
    }.addTo(jobs)

    override fun onCleared() {
        jobs.forEach { it.cancel()}
        super.onCleared()
    }

}

/**
 * 扩展函数，用于viewModel中的job添加到list方便
 */
private fun Job.addTo(list: MutableList<Job>){
    list.add(this)
}