package com.wei.login



import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseActivity
import com.wei.login.databinding.ActivityLoginBinding
import retrofit2.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @ClassName LoginActivity
 * @Author Rookie Wai
 * @Date 2021/7/26 8:47
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

class LoginActivity :BaseActivity<ActivityLoginBinding>(){

    private val viewModel:LoginActivityViewModel by viewModel()

    override fun getLayoutRes()=R.layout.activity_login


    override fun initView() {
        super.initView()
        mBinding.apply {
            vm=viewModel
        }
    }
    override fun initConfig() {
        super.initConfig()
        viewModel.apply {
            liveLoginRsp.observerKt {
                ToastUtils.showShort("登录结果"+it.toString())

            }
        }
    }

}




