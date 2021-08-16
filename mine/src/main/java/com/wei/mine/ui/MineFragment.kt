package com.wei.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.launcher.ARouter
import com.wei.common.base.BaseFragment
import com.wei.common.network.config.gToken
import com.wei.common.network.utils.MySpUtils
import com.wei.mine.R
import com.wei.mine.databinding.FragmentMineBinding
import kotlinx.android.synthetic.main.fragment_mine.*
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * @ClassName MineFragment
 * @Author Rookie Wai
 * @Date 2021/7/23 14:12
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MineFragment : BaseFragment(){

    private val viewModel:MineFragmentViewModel by viewModel()

    override fun getLayoutRes()= R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm=viewModel
            //登出
            btnLogoutMine.setOnClickListener {
                gToken.value=" "
                MySpUtils.remove("token")
                ARouter.getInstance().build("/login/login").navigation()
            }


            tvUserNameMine.setOnClickListener {
                val info = viewModel.liveInfo.value
                if (info == null) {
                    ARouter.getInstance().build("/login/login").navigation()
                } else {
                    val action =
                        MineFragmentDirections.actionMineFragmentToUserInfoFragment()
                    findNavController().navigate(action)

                }
            }

        }

    }


    override fun initData() {
        super.initData()
        gToken.observerKt {
            viewModel.getUserInfo()
        }
    }



}