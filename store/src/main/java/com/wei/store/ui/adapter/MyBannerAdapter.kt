package com.wei.store.ui.adapter

import android.util.MutableInt
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 * @ClassName BannerAdapter
 * @Author Rookie Wai
 * @Date 2021/8/16 10:55
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
/**
 * 商品详情页的轮播适配器
 */
class MyBannerAdapter(private val dataList:MutableList<String>): BannerImageAdapter<String>(dataList) {

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
