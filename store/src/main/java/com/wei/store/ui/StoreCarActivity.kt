package com.wei.store.ui

import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseActivity
import com.wei.store.R
import com.wei.store.databinding.ActivityStoreCarBinding
import com.wei.store.databinding.ItemStoreCarBinding
import com.wei.store.net.CarListRsp
import com.wei.store.ui.adapter.CarListRecyclerAdapter
import com.wei.store.ui.viewmodel.StoreCarActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @ClassName StoreCarActivity
 * @Author Rookie Wai
 * @Date 2021/8/16 23:05
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreCarActivity : BaseActivity<ActivityStoreCarBinding>() {

    private val viewModel : StoreCarActivityViewModel by viewModel()

    override fun getLayoutRes() = R.layout.activity_store_car

    private val carListAdapter=CarListRecyclerAdapter{

    }

    private lateinit var mItemBinding:ItemStoreCarBinding

    override fun initConfig() {
        super.initConfig()
        mBinding.apply {
            rvCar.adapter=carListAdapter

            toolbar.setNavigationOnClickListener {
                finish()
            }
        }

        viewModel.apply {
            getCarList()
            liveCarList.observerKt {
                if (it != null) {
                    carListAdapter.submitClear(it,true)
                }
            }
        }


    }

}