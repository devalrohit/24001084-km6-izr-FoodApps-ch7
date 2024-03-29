package com.catnip.appfood_rohit.data.repository

import com.catnip.appfood_rohit.data.datasource.category.CategoryDataSource
import com.catnip.appfood_rohit.data.model.Category

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryRepository {
    fun getCategories(): List<Category>
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override fun getCategories(): List<Category> = dataSource.getCategories()
}