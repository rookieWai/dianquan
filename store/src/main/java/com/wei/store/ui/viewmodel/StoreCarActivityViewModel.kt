package com.wei.store.ui.viewmodel

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.databinding.OnRebindCallback
import androidx.lifecycle.MutableLiveData
import com.wei.common.base.BaseViewModel
import com.wei.store.net.CarListRsp
import com.wei.store.repo.StoreRepo

/**
 * @ClassName ProductCarActiviyViewModel
 * @Author Rookie Wai
 * @Date 2021/8/16 22:56
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreCarActivityViewModel(private val repo: StoreRepo) : BaseViewModel() {

    val liveCarList=repo.liveCarList

    //价格总和
    var observablePrice = ObservableDouble(0.0)


    //获取用户购物车列表
    fun getCarList(callback: (dataList:ArrayList<CarListRsp.CarListRspItem>) -> Unit){
        serverAwait {
            repo.getCarList(callback={
                callback.invoke(it)
            })
        }
    }

    //修改购物车商品数量
    fun updateCarProductQuantity(id:Int,quantity:Int,callback:()->Unit){
        serverAwait {
            repo.updateCarProductQuantity(id,quantity,callback = {
                //修改成功后的处理逻辑
                callback.invoke()
            })
        }
    }

    fun clearCar(callback: () -> Unit){
        serverAwait {
            repo.clearCar(callback={
                //清空成功后的逻辑
                callback.invoke()
            })
        }

    }
    fun deleteCarByListId(list:List<Int>,callback: () -> Unit){
        serverAwait {
            repo.deleteCarByListId(list,callback={
                //删除成功后的逻辑
                callback.invoke()
            })
        }
    }

    fun plusPrice(price:Double,quantity:Int){
        observablePrice.set(observablePrice.get()+price*quantity)
    }

    fun minusPrice(price:Double,quantity:Int){
        observablePrice.set(observablePrice.get()-price*quantity)
    }

}