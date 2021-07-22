package com.wei.common.model

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @ClassName SingleLiveData
 * @Author Rookie Wai
 * @Date 2021/7/22 16:08
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 *
 *单响应的LiveData，只有一个接受者能接收到信息，可以避免不必要业务的场景中的事件消费通知
 *只有调用call的时候，observer才能收到消息
 */

class SingleLiveData<T> : MutableLiveData<T>() {
    //原子化的标记
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, obsrver: Observer<in T>) {

        //如果有活跃的观察者
        if (hasActiveObservers()) {
            Log.w(TAG, "多个观察者存在的时候，只有一个被通知到更新数据")
        }

        super.observe(owner, Observer {
            //判断标记是否被设置为true，是才会通知回调数据
            if (mPending.compareAndSet(true, false)) {
                obsrver.onChanged(it)
            }
        })
    }

    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveData"
    }
}