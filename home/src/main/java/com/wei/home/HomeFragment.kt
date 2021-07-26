package com.wei.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.home.databinding.FragmentHomeBinding

/**
 * @ClassName FragmentHome
 * @Author Rookie Wai
 * @Date 2021/7/23 17:03
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutRes()=R.layout.fragment_home

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view)

    }

}