package com.wei.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.mine.databinding.FragmentHostMineBinding

/**
 * @ClassName MineHostFragment
 * @Author Rookie Wai
 * @Date 2021/8/11 16:53
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MineHostFragment : BaseFragment(){

    override fun getLayoutRes()=R.layout.fragment_host_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHostMineBinding.bind(view)
    }

}