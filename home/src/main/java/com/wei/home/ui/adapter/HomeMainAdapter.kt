package com.wei.home.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.wei.home.databinding.ItemRecyclerHomeBinding
import com.wei.home.net.HomeContentRsp
import kotlinx.android.synthetic.main.item_recycler_home.view.*

/**
 * @ClassName HomeMainApater
 * @Author Rookie Wai
 * @Date 2021/8/20 20:59
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 * 主页主适配器
 */
class HomeMainAdapter : RecyclerView.Adapter<HomeMainAdapter .HomeAdapterVH>() {

    private val homeList = ArrayList<Any>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeAdapterVH.create(parent)

    override fun onBindViewHolder(holder: HomeAdapterVH, position: Int) {
        holder.bind(homeList[position] as ArrayList<Any>)
    }

    override fun getItemCount() = homeList.size

    //更新Recyclerview数据
    fun upRecyclerViewList(homeList: ArrayList<Any>) {
        this.homeList.clear()
        this.homeList.addAll(homeList)
        notifyDataSetChanged()
    }

    //清空RecyclerView数据
    fun clearRecyclerViewList() {
        this.homeList.clear()
        notifyDataSetChanged()
    }

    //Recyclerview中嵌套Recyclerview
    class HomeAdapterVH(val binding: ItemRecyclerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): HomeAdapterVH {
                val mBinding =
                    ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeAdapterVH(mBinding)
            }
        }

        fun bind(homeData: ArrayList<Any>) {
            setAdapter(homeData)
            binding.executePendingBindings()
        }

        //判断应该创建哪种布局
        private fun setAdapter(data:ArrayList<Any>) {
            LogUtils.i(data.javaClass)
            binding.rvItemHome.adapter = when(data[0]) {
                //热门品牌
                is HomeContentRsp.Brand-> {
                    binding.title="热门品牌"
                    binding.rvItemHome.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
                    val list = ArrayList<HomeContentRsp.Brand>()
                    list.addAll(data as ArrayList<HomeContentRsp.Brand>)
                    HomeBrandAdapter(list)
                }
                //热门商品
                is HomeContentRsp.HotProduct-> {
                    binding.title="热门商品"
                    binding.rvItemHome.layoutManager = GridLayoutManager(itemView.context, 2)
                    val list = ArrayList<HomeContentRsp.HotProduct>()
                    list.addAll(data as ArrayList<HomeContentRsp.HotProduct>)
                    HomeHotProductAdapter(list)
                }
                //推荐商品
                is HomeContentRsp.NewProduct-> {
                    binding.title="推荐商品"
                    binding.rvItemHome.layoutManager = GridLayoutManager(itemView.context, 2)
                    val list = ArrayList<HomeContentRsp.NewProduct>()
                    list.addAll(data as ArrayList<HomeContentRsp.NewProduct>)
                    HomeNewProductAdapter(list)
                }
                else -> error("error type{${data.javaClass}}")
            }

        }

    }


}