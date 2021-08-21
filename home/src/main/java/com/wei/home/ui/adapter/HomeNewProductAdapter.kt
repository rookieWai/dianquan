package com.wei.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wei.home.databinding.ItemHomeHotProductBinding
import com.wei.home.databinding.ItemHomeNewProductBinding
import com.wei.home.net.HomeContentRsp

/**
 * @ClassName HomeNewProduct
 * @Author Rookie Wai
 * @Date 2021/8/20 20:55
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
/**
 * 首页新品推荐适配器
 */
class HomeNewProductAdapter(private val mList: List<HomeContentRsp.NewProduct>) : RecyclerView.Adapter<HomeNewProductAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH.create(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(mList[position])
        //点击事件
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount() = mList.size


    class VH(private val binding: ItemHomeNewProductBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): VH {
                val itemBinding =
                    ItemHomeNewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VH(itemBinding)
            }
        }

        fun bind(info: HomeContentRsp.NewProduct) {
            binding.info = info
            binding.executePendingBindings()
        }
    }
}