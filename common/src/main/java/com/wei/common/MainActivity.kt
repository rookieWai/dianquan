package com.wei.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @ClassName MainActivity
 * @Author Rookie Wai
 * @Date 2021/7/18 20:36
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpApi:IHttp=OkHttpApi()

        httpApi.get(null,"home/content",object : IHttpCallback{
            override fun onSuccess(data: Any?) {
                runOnUiThread {
                    tv.text=data.toString()
                    LogUtils.e("success result:${data.toString()}")
                }
            }

            override fun onFailed(error: Any?) {
                LogUtils.d("failed msg:${error.toString()}")
            }

        })

    }
}