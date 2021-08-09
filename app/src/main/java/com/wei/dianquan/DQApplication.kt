package com.wei.dianquan

import com.alibaba.android.arouter.launcher.ARouter
import com.wei.common.base.BaseApplication
import com.wei.login.moduleLogin
import com.wei.service.network.moduleService
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

/**
 * @ClassName DQApplication
 * @Author Rookie Wai
 * @Date 2021/8/9 20:53
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class DQApplication : BaseApplication(){

    private val modules= arrayListOf<Module>(
        moduleService,moduleLogin
    )


    override fun initConfig() {
        super.initConfig()

        //添加common 模块之外的其他模块，组件koin的modules
        loadKoinModules(modules)

    }


}