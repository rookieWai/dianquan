package com.wei.store.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wei.store.databinding.ItemStoreCarBinding
import com.wei.store.net.CarListRsp
import org.intellij.lang.annotations.JdkConstants

/**
 * @ClassName CarListAdapter
 * @Author Rookie Wai
 * @Date 2021/8/16 22:28
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class CarListRecyclerAdapter(private val callback: (View, List<CarListRsp.CarListRspItem>, Int,HashMap<Int,Boolean>) -> Unit
) : RecyclerView.Adapter<CourseVH>() {

    val mList = mutableListOf<CarListRsp.CarListRspItem>()

    val map=HashMap<Int,Boolean>()

    var totalPrices:Double=0.0

    /*
    * recyclervice
    * clear 是否清空list初始化
    * */
    fun submitClear(list: List<CarListRsp.CarListRspItem> , clear: Boolean = false) {
        if (clear) mList.clear()
        mList.addAll(list)
        var num = 0
        mList.forEach { _ ->
            map[num++]=false
        }
        totalPrices=0.0
        list.forEach{
            totalPrices+=it.price*it.quantity
        }
        notifyDataSetChanged()
    }

    /**
     * 局部更新
     */
    fun submitPart(list: List<CarListRsp.CarListRspItem>,positions:Int) {
        mList.clear()
        mList.addAll(list)
        totalPrices=0.0
        list.forEach{
            totalPrices+=it.price*it.quantity
        }
        notifyItemChanged(positions)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CourseVH.createVH(parent)

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        holder.bind(mList[position])
        callback.invoke(holder.itemView,mList,position,map)
    }

    override fun getItemCount() = mList.size
}



class CourseVH(private val binding: ItemStoreCarBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun createVH(parent: ViewGroup): CourseVH {
            return CourseVH(
                ItemStoreCarBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(info: CarListRsp.CarListRspItem) {
        binding.info = info
        binding.executePendingBindings()
    }

}