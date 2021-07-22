package com.wei.common.network.support

import androidx.lifecycle.LiveData
import com.wei.common.network.model.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @ClassName LiveDataCallAdapterFactory
 * @Author Rookie Wai
 * @Date 2021/7/22 15:44
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
class LiveDataCallAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(getRawType(returnType) != LiveData::class.java){
            return null
        }

        val observableType= getParameterUpperBound(0,returnType as ParameterizedType)
        val rawObservableType= getRawType(observableType)
        if(rawObservableType!= ApiResponse::class.java){
            throw IllegalArgumentException("type must be a resource")
        }
        if(observableType !is ParameterizedType){
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType= getParameterUpperBound(0,observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}