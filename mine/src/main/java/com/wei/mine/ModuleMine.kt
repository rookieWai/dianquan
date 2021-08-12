package com.wei.mine

import com.wei.common.network.RetrofitApi
import com.wei.common.network.config.BASE_URL
import com.wei.mine.net.MineService
import com.wei.mine.repo.MineRepo
import com.wei.mine.repo.MineRepoImpl
import com.wei.mine.ui.MineFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @ClassName ModuleMine
 * @Author Rookie Wai
 * @Date 2021/8/11 16:58
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
val moduleMine: Module = module {

    //service retrofit
    single {
        get<RetrofitApi> { parametersOf(BASE_URL) }.getService(MineService::class.java)
    }

    single{ MineRepoImpl(get()) } bind MineRepo::class

    viewModel { MineFragmentViewModel(get()) }

}
