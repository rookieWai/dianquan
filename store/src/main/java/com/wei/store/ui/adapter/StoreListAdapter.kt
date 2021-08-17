package com.wei.store.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wei.store.databinding.ItemStoreProductBinding
import com.wei.store.net.StoreProductRsp

/**
 * @ClassName StoreProductListAdapter
 * @Author Rookie Wai
 * @Date 2021/8/14 15:42
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class StoreProductListAdapter(private val callback: (StoreProductRsp.Data) -> Unit)
    : PagingDataAdapter<StoreProductRsp.Data,StoreProduceViewHolder>(diffCallback)
{
    override fun onBindViewHolder(holder: StoreProduceViewHolder, position: Int) {
        getItem(position)?.let{ itemData ->
            //绑定数据
            holder.bind(itemData)
            //点击事件，使用时通过callback获取上下文
            holder.itemView.setOnClickListener {
                callback.invoke(itemData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreProduceViewHolder {
        return StoreProduceViewHolder.create(parent)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<StoreProductRsp.Data>() {
            override fun areItemsTheSame(
                oldItem: StoreProductRsp.Data,
                newItem: StoreProductRsp.Data
            ) = oldItem.id == newItem.id
            //            ) = false

            override fun areContentsTheSame(
                oldItem: StoreProductRsp.Data,
                newItem: StoreProductRsp.Data
            ) = oldItem == newItem
        }

    }
}


class StoreProduceViewHolder(private val binding: ItemStoreProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): StoreProduceViewHolder {
            return StoreProduceViewHolder(
                ItemStoreProductBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(data:  StoreProductRsp.Data?) {
        binding.info = data
        //强制执行绑定
        binding.executePendingBindings()
    }
}