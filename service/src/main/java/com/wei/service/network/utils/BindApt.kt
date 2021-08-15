package com.wei.service.network.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wei.service.R

/**
 * @ClassName BindApt
 * @Author Rookie Wai
 * @Date 2021/8/14 16:40
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
//项目适配用的BindAdapter

/**
 * imageView支持图片加载 绑定
 */
@BindingAdapter("app:srcCompat",requireAll = false)
fun imgSrc(iv: ImageView, src:Any?){
    //src为空，显示的默认图片p

    val imgRes=src?: R.drawable.xiaomi

    Glide.with(iv)
        .load(imgRes)
        .into(iv)

}