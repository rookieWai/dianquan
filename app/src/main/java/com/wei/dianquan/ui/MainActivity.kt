package com.wei.dianquan.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.findNavController

import com.wei.dianquan.R
import com.wei.dianquan.databinding.ActivityMainBinding


/**
 * @ClassName com.wei.dianquan.ui.MainActivty
 * @Author Rookie Wai
 * @Date 2021/7/23 17:54
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding =
            DataBindingUtil.setContentView<ActivityMainBinding> (this,
                R.layout.activity_main)


        val navController=findNavController(R.id.nav_host_fragment_activity_main)
        mBinding.navView.setupWithNavController(navController)

    }
}