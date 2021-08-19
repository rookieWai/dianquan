package com.wei.store.ui


import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseActivity
import com.wei.common.ktx.viewLifeCycleOwner
import com.wei.common.network.utils.MySpUtils
import com.wei.store.R
import com.wei.store.databinding.ActivityProductDetailBinding
import com.wei.store.net.ProductToCarData
import com.wei.store.ui.adapter.MyBannerAdapter
import com.wei.store.ui.viewmodel.ProductDetailActivityViewModel
import com.wei.store.utils.SplitString
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_store_car.*
import kotlinx.android.synthetic.main.item_store_product.*
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

    private val viewModel: ProductDetailActivityViewModel by viewModel()

    override fun getLayoutRes()= R.layout.activity_product_detail

    private lateinit var data : ProductToCarData

    //轮播图列表
    private val bannerList = ArrayList<String>()

    //轮播适配器
    private val bannerAdapter by lazy { MyBannerAdapter(bannerList) }

    override fun initConfig() {
        super.initConfig()
        mBinding.apply {
            vm=viewModel
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

            //添加商品到购物车按钮
            btToCar.setOnClickListener  {
                //判断登录状态
                if(MySpUtils.getString("token","0")=="0"){
                    ToastUtils.setGravity(0,0,0)
                    ToastUtils.showShort("请先登录")
                }else{
                    viewModel.addProductToCar(data)
                }
            }

        }


        viewModel.apply {
            //获取商品详情
            getProductDetail(intent.getIntExtra("id",26))
        }


    }

    override fun initData() {
        super.initData()
        viewModel.apply {
            //加载进度条显示
            isLoading.observe(viewLifeCycleOwner) {
                //协程block获取数据代码块是否结束，协程结束时为false
                mBinding.pbProductDetail.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }

            //添加轮播图的数据
            liveProductDetailRsp.observerKt {
                //后台返回的数据为String，将其转成列表
                val list= SplitString.getSplitString(it?.product?.albumPics)
                bannerList.clear()
                bannerList.addAll(list)
                bannerAdapter.notifyDataSetChanged()

                it?.product?.apply {
                    data= ProductToCarData(
                        price?.toDouble(),
                        id,
                        name,
                        intent.getStringExtra("pic"),
                        subTitle,
                        1
                        )
                }

            }
        }

    }

}