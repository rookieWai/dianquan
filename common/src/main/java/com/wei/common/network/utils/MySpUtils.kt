package com.wei.common.network.utils


import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.PathUtils
import com.tencent.mmkv.MMKV


/**
 * @ClassName MySputils
 * @Author Rookie Wai
 * @Date 2021/7/26 12:41
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/*
 * 自定义的key-value 轻量数据存储管理类，便于替换
 */
object MySpUtils {

    //用腾讯的MMKV
    private val kv: MMKV by lazy {
        MMKV.defaultMMKV()!!
    }

    init {
        MMKV.initialize(PathUtils.getInternalAppFilesPath())
    }

    fun put(key: String, value: Any?) {
        when (value) {
            is Boolean -> kv.putBoolean(key, value)
            is ByteArray -> kv.putBytes(key, value)
            is Float -> kv.putFloat(key, value)
            is Int -> kv.putInt(key, value)
            is Long -> kv.putLong(key, value)
            is String -> kv.putString(key, value)
            else -> error("${value?.javaClass?.simpleName} Not Supported By SpUtils")
        }
    }

    fun getBoolean(key: String, defValue: Boolean = false) = kv.getBoolean(key, defValue)

    fun getBytes(key: String, defValue: ByteArray? = null) = kv.getBytes(key, defValue)

    fun getFloat(key: String, defValue: Float = 0f) = kv.getFloat(key, defValue)

    fun getInt(key: String, defValue: Int = 0) = kv.getInt(key, defValue)

    fun getLong(key: String, defValue: Long = 0L) = kv.getLong(key, defValue)

    fun getString(key: String, defValue: String? = null) = kv.getString(key, defValue)

    fun remove(key: String) = kv.remove(key)

    fun removeValue(key: String) = kv.removeValueForKey(key)

}
