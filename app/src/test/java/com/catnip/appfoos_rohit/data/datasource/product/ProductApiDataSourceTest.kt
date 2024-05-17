package com.catnip.appfoos_rohit.data.datasource.product

import com.catnip.appfood_rohit.data.datasource.product.ProductApiDataSource
import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.source.network.services.AppFoodRohitApiService
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductResponse
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutRequestPayload
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductApiDataSourceTest {
    @MockK
    lateinit var service: AppFoodRohitApiService

    private lateinit var ds: ProductDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = ProductApiDataSource(service)
    }

    @Test
    fun getProducts() {
        runTest {
            val mockResponse = mockk<ProductResponse>(relaxed = true)
            coEvery { service.getProduct(any()) } returns mockResponse
            val actualResponse = ds.getProducts("makanan")
            coVerify { service.getProduct(any()) }
            assertEquals(actualResponse, mockResponse)
        }
    }

    @Test
    fun createOrder() {
        runTest {
            val mockRequest = mockk<CheckoutRequestPayload>(relaxed = true)
            val mockResponse = mockk<CheckoutResponse>(relaxed = true)
            coEvery { service.createOrder(any()) } returns mockResponse
            val actualResponse = ds.createOrder(mockRequest)
            coVerify { service.createOrder(any()) }
            assertEquals(actualResponse, mockResponse)
        }
    }
}
