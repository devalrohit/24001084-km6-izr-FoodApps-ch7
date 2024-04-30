package com.catnip.appfoos_rohit.data.source.network.model.products

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: List<ProductItemResponse>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?,
)
