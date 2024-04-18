package com.catnip.appfoos_rohit.data.source.network.model.products


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductItemResponse(
    @SerializedName("alamat_resto")
    val location: String?,
    @SerializedName("detail")
    val desc: String?,
    @SerializedName("harga")
    val price: Int?,
    @SerializedName("harga_format")
    val hargaFormat: String?,
    @SerializedName("image_url")
    val imgUrl: String?,
    @SerializedName("nama")
    val name: String?
)