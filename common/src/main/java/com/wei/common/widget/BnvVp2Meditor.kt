package com.wei.common.widget

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @ClassName BnvVp2Meditor
 * @Author Rookie Wai
 * @Date 2021/8/9 20:48
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class BnvVp2Meditor(
    private val bnv: BottomNavigationView,
    private  val vp2: ViewPager2,
    private val config:((BottomNavigationView, ViewPager2)->Unit)?=null
) {
    private val map = mutableMapOf<MenuItem,Int>()

    init{

        //使用map构建menu的每一个item与其位置的映射
        bnv.menu.forEachIndexed { index, item ->
            map[item] = index
        }

    }

    fun attach(){
        //用于bnv和vp2的配置
        config?.invoke(bnv,vp2)
        //viewPager页面切换时，同步到bottomNavigationView
        vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bnv.selectedItemId=bnv.menu.getItem(position).itemId
            }
        })

        //bottomNavigationView点击事件响应到viewPager
        bnv.setOnNavigationItemSelectedListener { item->
            vp2.currentItem=
                map[item]?:error("Bnv的item的Id${item.itemId}没有对应的ViewPager2的元素")
            true
        }

    }
}