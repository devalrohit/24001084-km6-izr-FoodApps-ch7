package com.catnip.appfood_rohit.data.repository

import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.mapper.toProducts
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfood_rohit.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductRepository {
    fun getProducts(categorySlug : String? = null): Flow<ResultWrapper<List<Product>>>
}

class ProductRepositoryImpl(
    private val dataSource: ProductDataSource
) : ProductRepository {
    override fun getProducts(categoryName : String?): Flow<ResultWrapper<List<Product>>> {
        return proceedFlow {
            dataSource.getProducts(categoryName).data.toProducts()
        }
    }
}