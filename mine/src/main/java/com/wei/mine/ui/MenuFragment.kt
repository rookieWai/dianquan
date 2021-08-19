package com.wei.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.mine.R
import com.wei.mine.databinding.FragmentMenuBinding

/**
 * @ClassName MenuFragment
 * @Author Rookie Wai
 * @Date 2021/8/11 16:00
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MenuFragment : BaseFragment() {

    override fun getLayoutRes()= R.layout.fragment_menu

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMenuBinding.bind(view)
    }



}