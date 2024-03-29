package com.catnip.appfood_rohit.data.datasource.product

import com.catnip.appfood_rohit.data.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductDataSource {
    fun getProducts(): List<Product>
}