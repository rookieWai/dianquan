package com.wei.dianquan


import android.content.Intent
import android.os.Handler
import android.view.View
import com.wei.common.base.BaseActivity
import com.wei.common.ktx.context
import com.wei.dianquan.databinding.ActivitySplashBinding
import com.wei.store.databinding.ActivityStoreCarBinding
import com.wei.store.ui.ProductDetailActivity
import java.util.*


/**
 * @ClassName SplashActivity
 * @Author Rookie Wai
 * @Date 2021/8/22 16:32
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
/**
 *闪屏页面
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>(){
    override fun getLayoutRes()=R.layout.activity_splash

    override fun initConfig() {
        super.initConfig()
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        val handler=Handler()
        val intent=Intent(context, MainActivity::class.java)
        handler.postDelayed(
            {
                startActivity(intent)
                finish()
            },1500)
    }

}