package com.wei.home.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wei.home.databinding.ItemHomeBrandBinding
import com.wei.home.net.HomeContentRsp

/**
 * @ClassName HomeBrand
 * @Author Rookie Wai
 * @Date 2021/8/20 20:46
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 首页推荐品牌的适配器
 */
class HomeBrandAdapter(private val mList: List<HomeContentRsp.Brand>) : RecyclerView.Adapter<HomeBrandAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.create(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(mList[position])
        //点击事件
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount() = mList.size


    class VH(private val binding: ItemHomeBrandBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): VH {
                val itemBinding =
                    ItemHomeBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VH(itemBinding)
            }
        }

        fun bind(info: HomeContentRsp.Brand) {
            binding.info = info
            binding.executePendingBindings()
        }
    }
}