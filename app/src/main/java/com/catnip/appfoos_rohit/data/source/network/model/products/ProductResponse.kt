package com.catnip.appfoos_rohit.data.source.network.model.products


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: List<ProductItemResponse>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)