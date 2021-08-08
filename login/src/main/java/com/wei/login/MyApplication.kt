package com.wei.login

import android.app.Application
import com.wei.service.network.moduleService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @ClassName MyApplication
 * @Author Rookie Wai
 * @Date 2021/8/7 16:31
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MyApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(moduleLogin)
            modules(moduleService)
        }
    }
}