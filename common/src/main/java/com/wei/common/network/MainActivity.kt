package com.wei.common.network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.wei.common.R
import com.wei.common.network.support.IHttpCallback
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.FormBody
import okhttp3.MultipartBody
import java.util.*

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

        val httpApi: IHttp = OkHttpApi()



//        httpApi.get(map,"home/content",object : IHttpCallback {
//            override fun onSuccess(data: Any?) {
//                runOnUiThread {
//                    tv.text=data.toString()
//                    LogUtils.e("success result:${data.toString()}")
//                }
//            }
//
//            override fun onFailed(error: Any?) {
//                LogUtils.d("failed msg:${error.toString()}")
//            }
//
//        })




        httpApi.post(LoginReq(),"",object : IHttpCallback {
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

    data class LoginReq(
        val password:String="123456",
        val username:String="test"
    )


}