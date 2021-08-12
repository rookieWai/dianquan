package com.wei.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.mine.R
import com.wei.mine.databinding.FragmentOrderBinding

/**
 * @ClassName OrderFragment
 * @Author Rookie Wai
 * @Date 2021/8/11 16:01
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class OrderFragment : BaseFragment(){
    override fun getLayoutRes()= R.layout.fragment_order

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentOrderBinding.bind(view)
    }

}