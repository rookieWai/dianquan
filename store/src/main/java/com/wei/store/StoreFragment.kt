package com.wei.store

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.store.databinding.FragmentStoreBinding

/**
 * @ClassName FragmentStore
 * @Author Rookie Wai
 * @Date 2021/7/23 17:06
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreFragment : BaseFragment() {

    override fun getLayoutRes()=R.layout.fragment_store

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStoreBinding.bind(view)
    }

}