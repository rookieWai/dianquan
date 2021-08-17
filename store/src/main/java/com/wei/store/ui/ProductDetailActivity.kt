package com.wei.store.ui


import com.wei.common.base.BaseActivity
import com.wei.common.ktx.viewLifeCycleOwner
import com.wei.store.R
import com.wei.store.databinding.ActivityProductDetailBinding
import com.wei.store.ui.adapter.MyBannerAdapter
import com.wei.store.ui.viewmodel.ProductDetailActivityViewModel
import com.wei.store.utils.SplitString
import com.youth.banner.indicator.CircleIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @ClassName ProductDetailActivity
 * @Author Rookie Wai
 * @Date 2021/8/15 14:14
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>() {

    private val activityViewModel: ProductDetailActivityViewModel by viewModel()

    override fun getLayoutRes()= R.layout.activity_product_detail

    //轮播图列表
    private val bannerList = ArrayList<String>()

    //轮播适配器
    private val bannerAdapter by lazy { MyBannerAdapter(bannerList) }

    override fun initConfig() {
        super.initConfig()
        mBinding.apply {
            vm=activityViewModel
            //返回按钮点击事件
            toolbar.setNavigationOnClickListener {
                finish()
            }

            //设置轮播
            productDetailBanner.addBannerLifecycleObserver(viewLifeCycleOwner)//添加生命周期观察者
                .setAdapter(bannerAdapter).apply {
                    isAutoLoop(false)  //不自动轮播
                    indicator= CircleIndicator(context)//轮播图上的点
                }

        }


        activityViewModel.apply {
            //获取商品详情
            getProductDetail(intent.getIntExtra("id",26))
        }


    }

    override fun initData() {
        super.initData()
        activityViewModel.apply {
            //添加轮播图的数据
            liveProductDetailRsp.observerKt {
                //后台返回的数据为String，将其转成列表
                val list= SplitString.getSplitString(it?.product?.albumPics)
                bannerList.clear()
                bannerList.addAll(list)
                bannerAdapter.notifyDataSetChanged()
            }
        }

    }

}