package com.catnip.appfood_rohit.data.repository

import com.catnip.appfood_rohit.data.datasource.category.CategoryDataSource
import com.catnip.appfood_rohit.data.mapper.toCategories
import com.catnip.appfood_rohit.data.model.Category
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfood_rohit.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryRepository {
    fun getCategories(): Flow<ResultWrapper<List<Category>>>
}

class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource
) : CategoryRepository {
    override fun getCategories(): Flow<ResultWrapper<List<Category>>> {
        return proceedFlow { dataSource.getCategories().data.toCategories() }
    }
}