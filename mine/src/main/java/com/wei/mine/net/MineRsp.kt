package com.wei.mine.net


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UserInfoRsp(
    val birthday: String?,
    val city: String?,
    val createTime: String?,
    val gender: Int,
    val id: Int,
    val integration: Int,
    val job: String?,
    val memberLevelId: Int,
    val nickname: String?,
    val password: String?,
    val personalizedSignature: String?,
    val phone: String?,
    val status: Int,
    val username: String?
): Parcelable