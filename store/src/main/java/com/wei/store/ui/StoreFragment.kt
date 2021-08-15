package com.wei.store.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.tabs.TabLayout
import com.wei.common.base.BaseFragment
import com.wei.store.R
import com.wei.store.databinding.FragmentStoreBinding
import com.wei.store.net.StoreTabRsp
import com.wei.store.ui.adapter.StoreProductListAdapter
import kotlinx.coroutines.flow.collectLatest
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

    //用于存储tab类名和对应id
    private val map=HashMap<String,Int?>()


    private val storeProductListAdapter = StoreProductListAdapter {
        //处理点击商品item的响应逻辑
        ToastUtils.showShort(it.id)
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStoreBinding.bind(view).apply {

            rvProduct.adapter=storeProductListAdapter

            //搜索栏响应
            etSearch.setOnEditorActionListener { p0, p1, p2 ->
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    ToastUtils.showShort(etSearch.text)
                    //关闭软键盘
                    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow((context as Activity).currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                }

                true
            }

            //设置标签栏数据
            viewModel.liveStoreTabRsp.observerKt {
                //移除原有的标签
                tlStore.removeAllTabs()
                //循环遍历，将商品栏名称赋给tab
                for(i in it as ArrayList<StoreTabRsp.StoreTabRspItem>){
                    tlStore.addTab(tlStore.newTab().setText(i.name))
                    //一级，全部，商品类别id为null
                    map[i.name] = null
                    //二级
                    for(j in i.children!!) {
                        if (j != null) {
                            tlStore.addTab(tlStore.newTab().setText(j.name))
                            //保存对应类别名的id
                            map[j.name] = j.id
                        }
                    }
                }

            }

            //点击tab的响应事件
            tlStore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //点击后刷新数据，传入类别id
                    if (tab != null) {
                        refreshData(map[tab.text])
                    }
                }

                //tab未被点击时回调
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                //tab重新点击时回调
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.getTab()
    }

    //数据请求
    fun refreshData(id: Int? =null){
        //收集 Flow<PagingData> 并将其提交给 PagingDataAdapter
        lifecycleScope.launchWhenCreated {
            viewModel.getProductListById(id).collectLatest {
                storeProductListAdapter.submitData(it)
            }
        }
    }

}