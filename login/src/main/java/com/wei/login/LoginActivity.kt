package com.wei.login




import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wei.common.base.BaseActivity
import com.wei.common.ktx.viewLifeCycleOwner
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
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        viewModel.apply {
            liveLoginRsp.observerKt {
                //ToastUtils.showShort("登录结果"+it.toString())
                gToken.value=it?.tokenHead+it?.token
                MySpUtils.put("token", gToken.value)
                finish()
            }

            //加载进度条显示
            isLoading.observe(viewLifeCycleOwner) {
                //协程block获取数据代码块是否结束，协程结束时为false
                mBinding.pbLogin.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }

        }

    }

}




