package com.catnip.appfood_rohit.data.mapper

import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductItemResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
fun ProductItemResponse?.toProduct(): Product =
    Product(
        name = this?.name.orEmpty(),
        imgUrl = this?.imgUrl.orEmpty(),
        desc = this?.desc.orEmpty(),
        price = this?.price?.toDouble() ?: 0.0,
        location = this?.location.orEmpty(),
        locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
    )

fun Collection<ProductItemResponse>?.toProducts(): List<Product> =
    this?.map { it.toProduct() } ?: listOf()
