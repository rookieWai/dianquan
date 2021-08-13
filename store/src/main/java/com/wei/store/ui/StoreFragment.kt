package com.wei.store.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.tabs.TabLayout
import com.wei.common.base.BaseFragment
import com.wei.store.R
import com.wei.store.databinding.FragmentStoreBinding
import com.wei.store.net.StoreTabRsp
import okhttp3.internal.format
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @ClassName FragmentStore
 * @Author Rookie Wai
 * @Date 2021/7/23 17:06
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreFragment : BaseFragment() {

    private val viewModel:StoreFragmentViewModel by viewModel()

    override fun getLayoutRes()= R.layout.fragment_store

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStoreBinding.bind(view).apply {

            etSearch.setOnEditorActionListener { p0, p1, p2 ->
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    ToastUtils.showShort(etSearch.text)

                    //关闭软键盘
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow((context as Activity).currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)


                }


                true
            }

            viewModel.liveStoreTabRsp.observerKt {
                tlStore.removeAllTabs()
                for(i in it as ArrayList<StoreTabRsp.StoreTabRspItem>){
                    tlStore.addTab(tlStore.newTab().setText(i.name))
                }
            }


            tlStore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    ToastUtils.showShort(tab?.text)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    ToastUtils.showShort(tab?.text)
                }

            })
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.getTab()
    }


}