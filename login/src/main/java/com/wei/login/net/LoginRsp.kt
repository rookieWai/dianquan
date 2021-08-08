package com.wei.login.net


import androidx.annotation.Keep

@Keep
data class LoginRsp(
    val tokenHead: String?,
    val token: String?
)