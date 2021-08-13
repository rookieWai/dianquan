package com.wei.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @ClassName BaseFragment
 * @Author Rookie Wai
 * @Date 2021/7/22 15:58
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 * 公共的基础Fragment
 */


abstract class BaseFragment : Fragment{

    //空的构造方法
    constructor():super()


    private var mBinding:ViewDataBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding=bindView(view,savedInstanceState)
        mBinding?.lifecycleOwner=viewLifecycleOwner
        initConfig()
        initData()
    }

    //获取实际Fragment所绑定的layout
    @LayoutRes
    abstract fun getLayoutRes():Int

    //生成一个binding对象
    abstract fun bindView(view: View,savedInstanceState: Bundle?):ViewDataBinding

    //完成配置的方法
    open fun initConfig() {}

    //处理数据的方法
    open fun initData(){}

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

    //LiveData的observe扩展函数
    fun <T:Any> LiveData<T>.observerKt(block:(T?)->Unit){
        this.observe(viewLifecycleOwner, Observer {
            block.invoke(it)
        })
    }


}
