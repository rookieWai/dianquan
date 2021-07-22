package com.wei.common.model

import androidx.lifecycle.LiveData

/**
 * @ClassName AbsentLifeData
 * @Author Rookie Wai
 * @Date 2021/7/22 16:07
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 *一个空的liveData对象
 */
class AbsentLiveDataL<T : Any?> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T : Any?> create(): LiveData<T> {
            return AbsentLiveDataL()
        }
    }
}

