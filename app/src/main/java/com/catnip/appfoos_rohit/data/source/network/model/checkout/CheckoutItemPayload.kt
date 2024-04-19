package com.catnip.kokomputer.data.source.network.model.checkout

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class CheckoutItemPayload(
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("qty")
    val qty: String,
    @SerializedName("catatan")
    val catatan: Int,
    @SerializedName("harga")
    val harga: Int,
)
