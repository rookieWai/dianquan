package com.wei.home.ui.adapter

import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 * @ClassName HomeBannerAdapter
 * @Author Rookie Wai
 * @Date 2021/8/20 9:21
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 *
 * 主页顶端轮播图适配器
 */
class HomeBannerAdapter(private val dataList:MutableList<String>): BannerImageAdapter<String>(dataList) {

    override fun onBindView(
        holder: BannerImageHolder?,
        data: String?,
        position: Int,
        size: Int) {

        //为空直接返回
        holder?:return

        Glide.with(holder.itemView)
            .load(data)
            .into(holder.imageView)


        //点击事件
        holder.imageView.setOnClickListener {

        }
    }
}
