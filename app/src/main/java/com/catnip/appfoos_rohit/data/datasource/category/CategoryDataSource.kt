package com.catnip.appfood_rohit.data.datasource.category

import com.catnip.appfood_rohit.data.model.Category

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryDataSource {
    fun getCategories(): List<Category>
}