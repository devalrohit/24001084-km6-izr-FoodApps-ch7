package com.catnip.appfood_rohit.data.datasource.category

import com.catnip.appfood_rohit.data.source.network.services.AppFoodRohitApiService
import com.catnip.appfoos_rohit.data.source.network.model.category.CategoriesResponse

class CategoryApiDataSource(
    private val service: AppFoodRohitApiService,
) : CategoryDataSource {
    override suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }
}
