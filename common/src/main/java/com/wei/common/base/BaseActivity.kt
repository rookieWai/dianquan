package com.wei.common.base


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.wei.common.ktx.viewLifeCycleOwner

/**
 * @ClassName BaseActivity
 * @Author Rookie Wai
 * @Date 2021/7/22 15:52
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 *
 * 基础的Activity
 */
abstract class BaseActivity<ActBinding:ViewDataBinding> :AppCompatActivity{

    //空的构造方法
    constructor():super()

    protected lateinit var mBinding:ActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this,getLayoutRes())
        mBinding.lifecycleOwner=viewLifeCycleOwner

        initConfig()
        initView()
        initData()
    }

    //获取实际Activity所绑定的layout
    @LayoutRes
    abstract fun getLayoutRes():Int

    //完成配置的方法
    open fun initConfig(){}

    //处理视图的方法
    open fun initView() {}


    //处理数据的方法
    open fun initData(){}

    override fun onDestroy() {
        super.onDestroy()
        //判断 mBinding是否初始化
        if(this::mBinding.isInitialized){
            mBinding.unbind()
        }
    }

    //扩展LiveData的observer函数
    protected fun <T:Any> LiveData<T?>.observerKt(blokc:(T?)->Unit){
        this.observe(viewLifeCycleOwner, Observer {
            blokc.invoke(it)
        })
    }


}