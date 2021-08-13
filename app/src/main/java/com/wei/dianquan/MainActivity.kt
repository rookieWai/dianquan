package com.wei.dianquan



import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wei.common.base.BaseActivity
import com.wei.common.network.config.gToken
import com.wei.common.network.utils.MySpUtils
import com.wei.common.widget.BnvVp2Meditor
import com.wei.deal.DealFragment
import com.wei.dianquan.databinding.ActivityMainBinding
import com.wei.home.HomeFragment
import com.wei.mine.MineHostFragment
import com.wei.store.ui.StoreFragment
import com.wei.survey.SurveyFragment
import java.lang.IndexOutOfBoundsException


/**
 * @ClassName com.wei.dianquan.ui.MainActivty
 * @Author Rookie Wai
 * @Date 2021/7/23 17:54
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MainActivity :BaseActivity<ActivityMainBinding>(){

    override fun getLayoutRes()=R.layout.activity_main

    //可实现fragment的复用，这里不复用fragment，每一次重新创建
    private val fragments = mapOf<Int,ReFragment>(
        INDEX_HOME to { HomeFragment() },
        INDEX_STORE to { StoreFragment() },
        INDEX_SURVEY to { SurveyFragment() },
        INDEX_DEAL to { DealFragment() },
        INDEX_MINE to { MineHostFragment() }
    )

    //业务相关
    override fun initConfig() {
        super.initConfig()
        gToken.value=MySpUtils.getString("token"," ")

    }

    //视图相关
    override fun initView() {
        super.initView()
        mBinding.apply {
            vp2Main.adapter=MainViewPagerAdapter(this@MainActivity,fragments)
            //关联viewPager2和BottomNavigationView
            BnvVp2Meditor(navView,vp2Main) { bnv, vp2 ->
                vp2.isUserInputEnabled=false //是否可以滑动切换页面
            }.attach()

        }

    }

    //数据相关
    override fun initData() {
        super.initData()
    }

    companion object{
        const val INDEX_HOME=0//首页home的索引位置
        const val INDEX_STORE=1//商店的索引位置
        const val INDEX_SURVEY=2//测评的索引位置
        const val INDEX_DEAL=3//交易的索引位置
        const val INDEX_MINE=4//我的 索引位置
    }


}

/**
 * 首页viewPager2的适配器
 */
class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity
    ,private val fragments:Map<Int,ReFragment>):
    FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]?.invoke()?: throw IndexOutOfBoundsException("ViewPager接收参数index越界啦！")
    }

}


//类型别名定义
typealias  ReFragment=()->Fragment