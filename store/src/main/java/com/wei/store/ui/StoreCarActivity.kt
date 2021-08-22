package com.wei.store.ui


import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.wei.common.base.BaseActivity
import com.wei.common.ktx.context
import com.wei.common.ktx.viewLifeCycleOwner
import com.wei.store.R
import com.wei.store.databinding.ActivityStoreCarBinding
import com.wei.store.net.CarListRsp
import com.wei.store.ui.adapter.CarListRecyclerAdapter
import com.wei.store.ui.viewmodel.StoreCarActivityViewModel
import kotlinx.android.synthetic.main.activity_store_car.*
import kotlinx.android.synthetic.main.item_store_car.*
import kotlinx.android.synthetic.main.item_store_car.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.LinkedHashSet

/**
 * @ClassName StoreCarActivity
 * @Author Rookie Wai
 * @Date 2021/8/16 23:05
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
@Route(path = "/store/car")
class StoreCarActivity : BaseActivity<ActivityStoreCarBinding>() {

    private val viewModel : StoreCarActivityViewModel by viewModel()

    override fun getLayoutRes() = R.layout.activity_store_car


    //set维护所选择中的单项，传入此项数据的id(数据表中的key值)，可用与删除结算
    private var set:LinkedHashSet<Int> = LinkedHashSet()

    @RequiresApi(Build.VERSION_CODES.M)
    private val carListAdapter=CarListRecyclerAdapter{ view, dataList, position,map->
        mBinding.rvCar.setItemViewCacheSize(dataList.size-4)

        if(map[position] == true){
            view.ck_item.isChecked=true
            view.foreground=context.getDrawable(R.drawable.grey)
            Log.i("position",position.toString())
        }
        else{
            view.ck_item.isChecked=false
            view.foreground=null
        }

        view.ck_item.setOnClickListener {
            //如果单选框状态为true，将该item的数据id加入set
            if (view.ck_item.isChecked){
                map[position]=true
                set.add(dataList[position].id)
                //总价加上选中价格
                viewModel.plusPrice(dataList[position].price,dataList[position].quantity)
                //将这个item前景设置为灰色
                view.foreground=context.getDrawable(R.drawable.grey)
                //如果全部选择，将全选设置为选择
                if(set.size==dataList.size){
                    mBinding.selectAll.isChecked=true
                }
            } else {
                map[position]=false
                //总价减去取消价格
                viewModel.minusPrice(dataList[position].price,dataList[position].quantity)
                set.remove(dataList[position].id)
                //将这个item前景消除
                view.foreground=null
                //当全选时取消一个单选框，将全选设置为未选中
                if(set.size==dataList.size-1){
                    mBinding.selectAll.isChecked=false
                }
            }
        }




        //添加数量加号的点击事件
        view.bt_add.setOnClickListener {
            viewModel.apply {
                updateCarProductQuantity(dataList[position].id, dataList[position].quantity + 1,callback = {
                    //修改数量成功后更新列表
                    getCarList(callback = {
                        notifyItem(it,position)
                        if(map[position]==true){
                            viewModel.plusPrice(dataList[position].price,1)
                        }

                    })
                })

            }
        }

        //减少数量减号的点击事件
        view.bt_minus.setOnClickListener {
            if ( Integer.parseInt(view.et_quantity.text.toString())==1){
                ToastUtils.setGravity(0,0,0,)
                ToastUtils.showShort("商品不能再减少了")
            }else {
                viewModel.apply {
                    updateCarProductQuantity(dataList[position].id, dataList[position].quantity - 1,callback = {
                        //修改数量成功后更新列表
                        getCarList(callback = {
                            notifyItem(it,position)
                            if(map[position]==true){
                                viewModel.minusPrice(dataList[position].price,1)
                            }

                        })
                    })

                }
            }
        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initConfig() {
        super.initConfig()
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        mBinding.apply {
            vm=viewModel
            rvCar.adapter=carListAdapter
            viewModel.getCarList(callback = {
                viewModel.liveCarList.value?.let { carListAdapter.submitClear(it) }
            })


            //点击全选时，通知adapter将所有单选框选上
            selectAll.setOnClickListener {
                if(carListAdapter.mList.size>0){
                    if (selectAll.isChecked){
                        for(i in 0 until carListAdapter.map.size){
                            carListAdapter.map[i]=true
                        }

                        viewModel.observablePrice.set(carListAdapter.totalPrices)
                        carListAdapter.mList.forEach {
                            set.add(it.id)
                        }

                    }else{
                        for(i in 0 until carListAdapter.map.size){
                            carListAdapter.map[i]=false
                        }
                        viewModel.observablePrice.set(0.0)
                        set.clear()
                    }
                    carListAdapter.notifyDataSetChanged()
                }else{
                    mBinding.selectAll.isChecked=false
                    ToastUtils.setGravity(0,0,0,)
                    ToastUtils.showShort("没有商品可选择")
                }
            }



            toolbar.apply {
                //返回键点击事件
                setNavigationOnClickListener { finish()}
                //右上角菜单点击事件处理
                setOnMenuItemClickListener {
                    if(carListAdapter.mList.size>0){
                        when(it.itemId){
                            //清空购物车
                            R.id.delete_all-> {
                                myDialog(context,callBack = {
                                    viewModel.apply {
                                        clearCar {
                                            getCarList(callback = {
                                                carListAdapter.submitClear(it,true)
                                                mBinding.selectAll.isChecked=false
                                                viewModel.observablePrice.set(0.0)
                                            })
                                        }
                                    }
                                })
                            }
                        }
                    }else{
                        ToastUtils.setGravity(0,0,0,)
                        ToastUtils.showShort("没有商品可清除")
                    }

                    return@setOnMenuItemClickListener super.onOptionsItemSelected(it);
                }


            }


            //删除按钮点击事件
            bt_cart_delete.setOnClickListener {
                if(carListAdapter.mList.size>0){
                    if(set.size==0){
                        ToastUtils.setGravity(0,0,0)
                        ToastUtils.showShort("请选择商品")
                    }else{
                        viewModel.apply {
                            deleteCarByListId(ArrayList(set),callback = {
                                getCarList(callback = {
                                    viewModel.liveCarList.value?.let { carListAdapter.submitClear(it,true) }
                                    set.clear()
                                    viewModel.observablePrice.set(0.0)
                                    mBinding.selectAll.isChecked=false
                                })

                            })
                        }
                    }

                }else{
                    ToastUtils.setGravity(0,0,0)
                    ToastUtils.showShort("没有商品可删除")
                }

            }

            //结算按钮点击事件
            bt_cart_pay.setOnClickListener {
                ToastUtils.setGravity(0,0,0)
                ToastUtils.showShort("还未开放结算")
            }

            //数据列表为空，显示去购物按钮
            viewModel.liveCarList.observerKt {
                if (it != null) {
                    if (it.size==0){
                        btToGoShopping.visibility=View.VISIBLE
                    }else{
                        btToGoShopping.visibility=View.INVISIBLE
                    }
                }
            }

            btToGoShopping.setOnClickListener{
                finish()
            }

        }


    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initData() {
        super.initData()
        viewModel.apply {

            //加载进度条显示
            isLoading.observe(viewLifeCycleOwner) {
                //协程block获取数据代码块是否结束，协程结束时为false
                mBinding.pbStoreCar.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
//
//            liveCarList.observerKt {
//                if (it != null) {
//                    carListAdapter.submitClear(it,true)
//                }
//            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun notifyItem(list:List<CarListRsp.CarListRspItem>, position :Int){
        viewModel.liveCarList.value?.let { carListAdapter.submitPart(list,position) }
    }

}

//对话框
fun myDialog(context: Context,callBack:()->Unit){
    val builder=androidx.appcompat.app.AlertDialog.Builder(context)
    builder.setMessage("确定清空购物车？")
    builder.setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
        callBack.invoke()
    })
    builder.setNegativeButton("取消",null)

    builder.show()
}

