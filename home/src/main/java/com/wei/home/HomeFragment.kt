package com.wei.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wei.common.base.BaseFragment
import com.wei.common.ktx.viewLifeCycleOwner
import com.wei.home.databinding.FragmentHomeBinding
import com.wei.home.ui.HomeBannerAdapter
import com.wei.home.ui.HomeFragmentViewModel
import com.wei.home.ui.adapter.HomeMainAdapter
import com.youth.banner.indicator.CircleIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @ClassName FragmentHome
 * @Author Rookie Wai
 * @Date 2021/7/23 17:03
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutRes()=R.layout.fragment_home

    private val viewModel:HomeFragmentViewModel by viewModel()

    private val homeList = ArrayList<Any>()

    private val adapter: HomeMainAdapter = HomeMainAdapter()

    //轮播图列表
    private val bannerList = ArrayList<String>()

    //轮播适配器
    private val bannerAdapter by lazy { HomeBannerAdapter(bannerList) }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view).apply {
            rvHome.adapter=adapter

            //设置轮播
            homeBanner.addBannerLifecycleObserver(viewLifecycleOwner)//添加生命周期观察者
                .setAdapter(bannerAdapter).apply {
                    indicator= CircleIndicator(context)//轮播图上的点
                }



        }

    }

    override fun initConfig(){
        super.initData()
        viewModel.apply {
            getHomeContent()
        }

    }

    override fun initData() {
        super.initData()
        viewModel.apply {
            liveHomeContent.observerKt {
                if (it != null) {
                    it.brandList?.let { it1 -> homeList.add(it1) }
                    it.hotProductList?.let { it2->homeList.add(it2) }
                    it.newProductList?.let { it3->homeList.add(it3) }
                }
                adapter.upRecyclerViewList(homeList)
            }
        }
        val list = ArrayList<String>()
        list.apply {
            add("https://img-blog.csdnimg.cn/fdfd2015d3c044e78ccc824e301a44de.png")
            add("https://img-blog.csdnimg.cn/91ee3ff7707e4fc5a2cbd03dc2a1ed80.png")
            add("https://img-blog.csdnimg.cn/21dfef79b11b45689d5ec7a3268a02c3.png")
            add("https://img-blog.csdnimg.cn/1a72490efae0451a8f1bba06b511f6e9.png")
        }
        bannerList.addAll(list)
        bannerAdapter.notifyDataSetChanged()
    }

}