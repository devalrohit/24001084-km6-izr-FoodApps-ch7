package com.catnip.appfood_rohit.data.datasource.product

import com.catnip.appfoos_rohit.data.source.network.model.products.ProductResponse
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutRequestPayload
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductDataSource {
    suspend fun getProducts(categorySlug: String? = null): ProductResponse
    suspend fun createOrder(payload : CheckoutRequestPayload) : CheckoutResponse
}

