package com.wei.store

import com.wei.common.network.RetrofitApi
import com.wei.common.network.config.BASE_URL
import com.wei.store.net.StoreService
import com.wei.store.repo.StoreRepo
import com.wei.store.repo.StoreRepoImpl
import com.wei.store.ui.StoreFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @ClassName ModuleStore
 * @Author Rookie Wai
 * @Date 2021/8/13 20:50
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
val moduleStore: Module= module {

    //service retrofit
    single {
        get<RetrofitApi> { parametersOf(BASE_URL) }.getService(StoreService::class.java)
    }

    single { StoreRepoImpl(get()) } bind StoreRepo::class

    viewModel { StoreFragmentViewModel(get()) }
}