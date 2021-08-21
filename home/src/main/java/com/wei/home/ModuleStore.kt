package com.wei.home

import com.wei.common.network.RetrofitApi
import com.wei.common.network.config.BASE_URL
import com.wei.home.net.HomeService
import com.wei.home.repo.HomeRepo
import com.wei.home.repo.HomeRepoImpl
import com.wei.home.ui.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @ClassName ModuleStore
 * @Author Rookie Wai
 * @Date 2021/8/20 20:18
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
val moduleHome: Module = module {

    //service retrofit
    single {
        get<RetrofitApi> { parametersOf(BASE_URL) }.getService(HomeService::class.java)
    }

    single { HomeRepoImpl(get()) } bind HomeRepo::class

    viewModel { HomeFragmentViewModel(get()) }

}