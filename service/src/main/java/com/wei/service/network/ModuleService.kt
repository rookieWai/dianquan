package com.wei.service.network


import com.wei.common.network.RetrofitApi
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * @ClassName moduleService
 * @Author Rookie Wai
 * @Date 2021/8/6 18:25
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

val moduleService: Module = module {

    single<RetrofitApi>{ (host:String)->RetrofitApi.initConfig(host)}


}