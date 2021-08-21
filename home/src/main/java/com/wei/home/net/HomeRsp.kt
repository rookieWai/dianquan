package com.wei.home.net

import androidx.annotation.Keep

/**
 * @ClassName HomeRsp
 * @Author Rookie Wai
 * @Date 2021/8/20 19:54
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */
@Keep
data class HomeContentRsp(
    val advertiseList: List<Advertise?>?,
    val brandList: List<Brand?>?,
    val homeFlashPromotion: HomeFlashPromotion?,
    val hotProductList: List<HotProduct?>?,
    val newProductList: List<NewProduct?>?,
    val subjectList: List<Any?>?
) {
    @Keep
    data class Advertise(
        val clickCount: Int,
        val endTime: String?,
        val id: Int,
        val name: String?,
        val note: String?,
        val orderCount: Int,
        val pic: String?,
        val sort: Int,
        val startTime: String?,
        val status: Int,
        val type: Int,
        val url: String?
    )

    @Keep
    data class Brand(
        val bigPic: String?,
        val factoryStatus: Int,
        val firstLetter: String?,
        val id: Int,
        val logo: String?,
        val name: String?,
        val productCommentCount: Int,
        val productCount: Int,
        val showStatus: Int,
        val sort: Int
    )

    @Keep
    class HomeFlashPromotion

    @Keep
    data class HotProduct(
        val albumPics: String?,
        val brandId: Int,
        val brandName: String?,
        val deleteStatus: Int,
        val description: String?,
        val detailTitle: String?,
        val feightTemplateId: Int,
        val giftGrowth: Int,
        val giftPoint: Int,
        val id: Int,
        val keywords: String?,
        val lowStock: Int,
        val name: String?,
        val newStatus: Int,
        val note: String?,
        val originalPrice: Int,
        val pic: String?,
        val previewStatus: Int,
        val price: Int,
        val productAttributeCategoryId: Int,
        val productCategoryId: Int,
        val productCategoryName: String?,
        val productSn: String?,
        val promotionPerLimit: Int,
        val promotionType: Int,
        val publishStatus: Int,
        val recommandStatus: Int,
        val sale: Int,
        val serviceIds: String?,
        val sort: Int,
        val stock: Int,
        val subTitle: String?,
        val unit: String?,
        val usePointLimit: Int,
        val verifyStatus: Int,
        val weight: Int
    )

    @Keep
    data class NewProduct(
        val albumPics: String?,
        val brandId: Int,
        val brandName: String?,
        val deleteStatus: Int,
        val description: String?,
        val detailTitle: String?,
        val feightTemplateId: Int,
        val giftGrowth: Int,
        val giftPoint: Int,
        val id: Int,
        val keywords: String?,
        val lowStock: Int,
        val name: String?,
        val newStatus: Int,
        val note: String?,
        val originalPrice: Int,
        val pic: String?,
        val previewStatus: Int,
        val price: Int,
        val productAttributeCategoryId: Int,
        val productCategoryId: Int,
        val productCategoryName: String?,
        val productSn: String?,
        val promotionPerLimit: Int,
        val promotionType: Int,
        val publishStatus: Int,
        val recommandStatus: Int,
        val sale: Int,
        val serviceIds: String?,
        val sort: Int,
        val stock: Int,
        val subTitle: String?,
        val unit: String?,
        val usePointLimit: Int,
        val verifyStatus: Int,
        val weight: Int
    )
}