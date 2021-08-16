package com.wei.store.net

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * @ClassName StoreTabRsp
 * @Author Rookie Wai
 * @Date 2021/8/13 20:14
 *
 * CSDN->https://blog.csdn.net/weiwai
 * github->https://github.com/rookieWai
 */

/**
 *商品页面所用数据
 */

//商店tab
@Keep
class StoreTabRsp : ArrayList<StoreTabRsp.StoreTabRspItem>() {

    data class StoreTabRspItem(
        val children: ArrayList<childrenItem?>?,
        val icon: String?,
        val id: Int,
        val keywords: String?,
        val level: Int,
        val name: String,
        val navStatus: Int,
        val parentId: Int,
        val productCount: Int,
        val productUnit: String?,
        val showStatus: Int,
        val sort: Int
    )
}


@Keep
data class childrenItem(
    val children: ArrayList<childrenItem?>?,
    val icon: String?,
    val id: Int,
    val keywords: String?,
    val level: Int,
    val name: String,
    val navStatus: Int,
    val parentId: Int,
    val productCount: Int,
    val productUnit: String?,
    val showStatus: Int,
    val sort: Int
)


/**
 * 商品
 */
@Parcelize
@Keep
data class StoreProductRsp(
    @SerializedName("list")
    val datas: List<Data>?,
    val pageNum: Int,
    val pageSize: Int,
    val total: Int,
    val totalPage: Int
):Parcelable {
    @Keep
    @Parcelize
    data class Data(
        val albumPics: String?,
        val brandId: Int,
        val brandName: String?,
        val deleteStatus: Int,
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
    ):Parcelable

}


/**
 * 商品详情的回调数据
 */
@Parcelize
@Keep
data class ProductDetailRsp(
    val brand: Brand?,
    val couponList: List<@RawValue Any?>?,
    val product: Product?,
    val productAttributeList: List<ProductAttribute?>?,
    val productAttributeValueList: List<ProductAttributeValue?>?,
    val skuStockList: List<SkuStock?>?
):Parcelable {
    @Parcelize
    @Keep
    data class Brand(
        val brandStory: String?,
        val factoryStatus: Int,
        val firstLetter: String?,
        val id: Int,
        val logo: String?,
        val name: String?,
        val productCommentCount: Int,
        val productCount: Int,
        val showStatus: Int,
        val sort: Int
    ):Parcelable

    @Parcelize
    @Keep
    data class Product(
        val albumPics: String?,
        val brandId: Int,
        val brandName: String?,
        val deleteStatus: Int,
        val description: String?,
        val detailDesc: String?,
        val detailHtml: String?,
        val detailMobileHtml: String?,
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
    ):Parcelable

    @Parcelize
    @Keep
    data class ProductAttribute(
        val filterType: Int,
        val handAddStatus: Int,
        val id: Int,
        val inputList: String?,
        val inputType: Int,
        val name: String?,
        val productAttributeCategoryId: Int,
        val relatedStatus: Int,
        val searchType: Int,
        val selectType: Int,
        val sort: Int,
        val type: Int
    ):Parcelable

    @Parcelize
    @Keep
    data class ProductAttributeValue(
        val id: Int,
        val productAttributeId: Int,
        val productId: Int,
        val value: String?
    ):Parcelable

    @Parcelize
    @Keep
    data class SkuStock(
        val id: Int,
        val lockStock: Int,
        val price: Int,
        val productId: Int,
        val skuCode: String?,
        val spData: String?,
        val stock: Int
    ):Parcelable
}



