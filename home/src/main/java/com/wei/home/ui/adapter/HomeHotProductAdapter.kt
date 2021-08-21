package com.wei.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wei.home.databinding.ItemHomeBrandBinding
import com.wei.home.databinding.ItemHomeHotProductBinding
import com.wei.home.net.HomeContentRsp

/**
 * @ClassName HomeHotProductAdapter
 * @Author Rookie Wai
 * @Date 2021/8/20 20:51
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
/**
 * 首页热门商品推荐适配器
 */
class HomeHotProductAdapter(private val mList: List<HomeContentRsp.HotProduct>) : RecyclerView.Adapter<HomeHotProductAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.create(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(mList[position])
        //点击事件
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount() = mList.size


    class VH(private val binding: ItemHomeHotProductBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): VH {
                val itemBinding =
                    ItemHomeHotProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VH(itemBinding)
            }
        }

        fun bind(info: HomeContentRsp.HotProduct) {
            binding.info = info
            binding.executePendingBindings()
        }
    }
}
