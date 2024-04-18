package com.catnip.appfood_rohit.data.mapper

import com.catnip.appfood_rohit.data.model.Category
import com.catnip.appfoos_rohit.data.source.network.model.category.CategoryItemResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun CategoryItemResponse?.toCategory() =
    Category(
        name = this?.nama.orEmpty(),
        imgUrl = this?.imageUrl.orEmpty(),

    )

fun Collection<CategoryItemResponse>?.toCategories() =
    this?.map { it.toCategory() } ?: listOf()