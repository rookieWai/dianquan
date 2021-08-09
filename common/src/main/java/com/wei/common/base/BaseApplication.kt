package com.wei.common.base

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @ClassName BaseApplication
 * @Author Rookie Wai
 * @Date 2021/8/9 20:52
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

abstract class BaseApplication : Application() {

    private var application: Application? = null

    override fun onCreate() {
        application=this
        super.onCreate()
        // koin注入框架初始化
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BaseApplication)
        }

        initConfig()
        initData()

        LogUtils.d("BaseApplication Create")
    }


    open fun initConfig() {

    }

    open fun initData() {

    }

}