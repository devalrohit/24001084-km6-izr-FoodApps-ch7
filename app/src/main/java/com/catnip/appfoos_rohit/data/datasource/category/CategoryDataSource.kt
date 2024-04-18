package com.catnip.appfood_rohit.data.datasource.category

import com.catnip.appfoos_rohit.data.source.network.model.category.CategoriesResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryDataSource {
    suspend fun getCategories(): CategoriesResponse
}