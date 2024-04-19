package com.catnip.appfood_rohit.data.datasource.product

import com.catnip.appfood_rohit.data.source.network.services.AppFoodRohitApiService
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductResponse
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutRequestPayload
import com.catnip.kokomputer.data.source.network.model.checkout.CheckoutResponse

class ProductApiDataSource(
    private val service: AppFoodRohitApiService
) : ProductDataSource {
    override suspend fun getProducts(categorySlug: String?): ProductResponse {
        return service.getProduct(categorySlug)
    }

    override suspend fun createOrder(payload: CheckoutRequestPayload): CheckoutResponse {
        TODO("Not yet implemented")
    }
}
