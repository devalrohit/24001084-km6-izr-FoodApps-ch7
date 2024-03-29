package com.catnip.appfood_rohit.data.repository

import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductRepository {
    fun getProducts(): List<Product>
}

class ProductRepositoryImpl(private val dataSource: ProductDataSource) : ProductRepository {
    override fun getProducts(): List<Product> = dataSource.getProducts()
}