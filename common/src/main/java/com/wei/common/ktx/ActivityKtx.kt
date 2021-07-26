package com.wei.common.ktx

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner

/**
 * @ClassName ActivityKtx
 * @Author Rookie Wai
 * @Date 2021/7/22 15:58
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */


//region 扩展属性

/**
 * 扩展lifeCycleOwner属性，便于和Fragment之间使用lifeCycleOwner一致性
 */
val ComponentActivity.viewLifeCycleOwner: LifecycleOwner
    get() = this


/**
 * Activity的扩展字段，便于Fragment中使用liveData类的时候，参数一致性
 */
val Activity.context: Context
    get() = this
//endregion
