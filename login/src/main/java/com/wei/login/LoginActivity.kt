package com.wei.login




import com.alibaba.android.arouter.facade.annotation.Route
import com.wei.common.base.BaseActivity
import com.wei.common.network.config.gToken
import com.wei.common.network.utils.MySpUtils
import com.wei.login.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @ClassName LoginActivity
 * @Author Rookie Wai
 * @Date 2021/7/26 8:47
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

@Route(path = "/login/login")
class LoginActivity :BaseActivity<ActivityLoginBinding>(){

    private val viewModel:LoginActivityViewModel by viewModel()

    override fun getLayoutRes()=R.layout.activity_login


    override fun initView() {
        super.initView()
        mBinding.apply {
            vm=viewModel
            toolBar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.apply {
            liveLoginRsp.observerKt {
                //ToastUtils.showShort("登录结果"+it.toString())
                gToken.value=it?.tokenHead+it?.token
                MySpUtils.put("token", gToken.value)
                finish()
            }
        }
    }

}




