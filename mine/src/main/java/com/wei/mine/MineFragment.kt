package com.wei.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.wei.common.base.BaseFragment
import com.wei.mine.databinding.FragmentMineBinding

/**
 * @ClassName MineFragment
 * @Author Rookie Wai
 * @Date 2021/7/23 14:12
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MineFragment : BaseFragment(){

    override fun getLayoutRes()=R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view)
    }





}