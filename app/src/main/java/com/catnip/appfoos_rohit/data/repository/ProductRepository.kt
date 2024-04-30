package com.catnip.appfood_rohit.data.repository

import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.mapper.toProducts
import com.catnip.appfood_rohit.data.model.Cart
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfood_rohit.utils.proceedFlow
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutItemPayload
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutRequestPayload
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(category: String? = null): Flow<ResultWrapper<List<Product>>>

    fun createOrder(
        profile: String,
        product: List<Cart>,
        totalPrice: Int,
    ): Flow<ResultWrapper<Boolean>>
}

class ProductRepositoryImpl(private val dataSource: ProductDataSource) : ProductRepository {
    override fun getProducts(category: String?): Flow<ResultWrapper<List<Product>>> {
        return proceedFlow { dataSource.getProducts(category).data.toProducts() }
    }

    override fun createOrder(
        profile: String,
        product: List<Cart>,
        totalPrice: Int,
    ): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            val response =
                dataSource.createOrder(
                    CheckoutRequestPayload(
                        total = totalPrice,
                        username = profile,
                        orders =
                            product.map {
                                CheckoutItemPayload(
                                    nama = it.productName,
                                    qty = it.itemQuantity,
                                    catatan = it.itemNotes.orEmpty(),
                                    harga = it.productPrice.toInt(),
                                )
                            },
                    ),
                )
            // Mengembalikan true jika respons sukses, false jika tidak
            response != null
        }
    }
}
