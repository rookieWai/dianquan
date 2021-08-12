package com.wei.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.mine.R
import com.wei.mine.databinding.FragmentUserInfoBinding

/**
 * @ClassName UserInfoFragment
 * @Author Rookie Wai
 * @Date 2021/8/11 16:01
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class UserInfoFragment :BaseFragment(){
    override fun getLayoutRes()= R.layout.fragment_user_info

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentUserInfoBinding.bind(view)
    }
}