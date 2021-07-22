package com.wei.common.network.config

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * @ClassName LocalCookieJar
 * @Author Rookie Wai
 * @Date 2021/7/20 13:50
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 *
 *
 * 用于持久化的cookieJar实现
 */
internal class LocalCookieJar: CookieJar {
    //cookie的本地存储
    private val cache= mutableListOf<Cookie>()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        //过期的Cookie
        val invalidCookies:MutableList<Cookie> = ArrayList()
        //有效的Cookie
        val validCookies:MutableList<Cookie> =ArrayList()

        for(cookie in cache){
            if(cookie.expiresAt<System.currentTimeMillis()){
                //判断是否过期
                invalidCookies.add(cookie)
            }else if (cookie.matches(url)){
                //匹配Cookie对应的url
                validCookies.add(cookie)
            }
        }

        //缓存中移除过期的Cookie
        cache.removeAll(invalidCookies)

        //返回List<Cookie>让Request进行设置
        return validCookies

    }

    /**
     * 将cookie保存
     */
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cache.addAll(cookies)
    }
}