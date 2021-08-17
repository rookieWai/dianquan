package com.wei.store.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.wei.store.databinding.ItemStoreCarBinding
import com.wei.store.net.CarListRsp

/**
 * @ClassName CarListAdapter
 * @Author Rookie Wai
 * @Date 2021/8/16 22:28
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class CarListRecyclerAdapter(private val callback: (CarListRsp.CarListRspItem) -> Unit) : RecyclerView.Adapter<CourseVH>() {

    private val mList = mutableListOf<CarListRsp.CarListRspItem>()

    /*
    * recyclervice
    * clear 是否清空list初始化
    * */
    fun submitClear(list: List<CarListRsp.CarListRspItem>, clear: Boolean = false) {
        if (clear) mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CourseVH.createVH(parent)

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        holder.bind(mList[position])
        holder.itemView.setOnClickListener {
            callback.invoke(mList[position])
        }
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