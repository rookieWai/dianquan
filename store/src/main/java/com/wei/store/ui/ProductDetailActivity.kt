package com.wei.store.ui


import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseActivity
import com.wei.store.R
import com.wei.store.databinding.ActivityProductDetailBinding
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

    private val viewModel:ProductDetailViewModel by viewModel()

    override fun getLayoutRes()= R.layout.activity_product_detail

    override fun initConfig() {
        super.initConfig()
        mBinding.apply {
            vm=viewModel
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }


        viewModel.getProductDetail(intent.getIntExtra("id",26))

    }

    override fun initData() {
        super.initData()


    }

}