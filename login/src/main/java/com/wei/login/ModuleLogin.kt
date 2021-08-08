package com.wei.login

import com.wei.common.network.RetrofitApi
import com.wei.common.network.config.BASE_URL
import com.wei.login.net.LoginService
import com.wei.login.repo.LoginRepo
import com.wei.login.repo.LoginRepoImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @ClassName moduleLogin
 * @Author Rookie Wai
 * @Date 2021/8/6 18:24
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
val moduleLogin: Module = module {

    //service retrofit
    single {
        get<RetrofitApi> { parametersOf(BASE_URL) }.getService(LoginService::class.java)
    }

    single{ LoginRepoImpl(get()) } bind LoginRepo::class

    viewModel { LoginActivityViewModel(get()) }

}
