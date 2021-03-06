package com.wei.deal

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.deal.databinding.FragmentDealBinding


/**
 * @ClassName DealFragment
 * @Author Rookie Wai
 * @Date 2021/7/22 16:40
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

class DealFragment :BaseFragment() {

    override fun getLayoutRes()=R.layout.fragment_deal

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentDealBinding.bind(view)
    }


}