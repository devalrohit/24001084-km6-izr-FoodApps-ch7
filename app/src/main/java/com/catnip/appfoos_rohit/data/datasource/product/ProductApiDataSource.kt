package com.catnip.appfood_rohit.data.datasource.product

import com.catnip.appfood_rohit.data.source.network.services.AppFoodRohitApiService
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/class ProductApiDataSource(
    private val service: AppFoodRohitApiService
) : ProductDataSource {
    override suspend fun getProducts(categorySlug: String?): ProductResponse {
        return service.getProduct(categorySlug)
    }
}
