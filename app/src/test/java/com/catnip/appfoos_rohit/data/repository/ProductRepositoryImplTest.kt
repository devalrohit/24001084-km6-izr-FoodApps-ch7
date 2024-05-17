package com.catnip.appfoos_rohit.data.repository

import app.cash.turbine.test
import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.model.Cart
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.data.repository.ProductRepositoryImpl
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductItemResponse
import com.catnip.appfoos_rohit.data.source.network.model.products.ProductResponse
import com.example.foodiesapp.data.source.network.model.checkout.CheckoutResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CatalogRepositoryImplTest {
    @MockK
    lateinit var dataSource: ProductDataSource
    private lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ProductRepositoryImpl(dataSource)
    }

    @Test
    fun `when get catalog success`() {
        val catalog1 =
            ProductItemResponse(
                hargaFormat = "asadasdsa",
                name = "dfsdfsdf",
                price = 8000,
                imgUrl = "fsdfsdfsdf",
                desc = "adfadfsdfsf",
                location = "asfsdfsdfd",
            )
        val catalog2 =
            ProductItemResponse(
                hargaFormat = "dfgdfgdfg",
                name = "dfsasdadfsdf",
                price = 4000,
                imgUrl = "fsaaadfsdfsdf",
                desc = "adfadaaafsdfsf",
                location = "asfaaasdfsdfd",
            )
        val mockListCatalog = listOf(catalog1, catalog2)
        val mockResponse = mockk<ProductResponse>()
        every { mockResponse.data } returns mockListCatalog
        runTest {
            coEvery { dataSource.getProducts(any()) } returns mockResponse
            repository.getProducts().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.getProducts(any()) }
            }
        }
    }

    @Test
    fun `when get catalog loading`() {
        val catalog1 =
            ProductItemResponse(
                hargaFormat = "asadasdsa",
                name = "dfsdfsdf",
                price = 8000,
                imgUrl = "fsdfsdfsdf",
                desc = "adfadfsdfsf",
                location = "asfsdfsdfd",
            )
        val catalog2 =
            ProductItemResponse(
                hargaFormat = "dfgdfgdfg",
                name = "dfsasdadfsdf",
                price = 4000,
                imgUrl = "fsaaadfsdfsdf",
                desc = "adfadaaafsdfsf",
                location = "asfaaasdfsdfd",
            )
        val mockListCatalog = listOf(catalog1, catalog2)
        val mockResponse = mockk<ProductResponse>()
        every { mockResponse.data } returns mockListCatalog
        runTest {
            coEvery { dataSource.getProducts(any()) } returns mockResponse
            repository.getProducts().map {
                delay(100)
                it
            }.test {
                delay(110)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { dataSource.getProducts(any()) }
            }
        }
    }

    @Test
    fun `when get catalog error`() {
        runTest {
            coEvery { dataSource.getProducts(any()) } throws IllegalStateException("Mock error")
            repository.getProducts().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { dataSource.getProducts(any()) }
            }
        }
    }

    @Test
    fun `when get catalog empty`() {
        val mockListCatalog = listOf<ProductItemResponse>()
        val mockResponse = mockk<ProductResponse>()
        every { mockResponse.data } returns mockListCatalog
        runTest {
            coEvery { dataSource.getProducts(any()) } returns mockResponse
            repository.getProducts().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Empty)
                coVerify { dataSource.getProducts(any()) }
            }
        }
    }

    @Test
    fun `when create order success`() {
        val mockCart =
            listOf(
                Cart(
                    id = 1,
                    productId = "asdasdasd",
                    productName = "sdfsdf",
                    productImgUrl = "sdfsdfsd",
                    productPrice = 10000.0,
                    itemQuantity = 2,
                    itemNotes = "dfgdsfgsddfg",
                ),
                Cart(
                    id = 2,
                    productId = "asaadasdasd",
                    productName = "sdfssddf",
                    productImgUrl = "sdfdsdfsd",
                    productPrice = 100000.0,
                    itemQuantity = 3,
                    itemNotes = "dfgdsfaagsddfg",
                ),
            )
        val totalPrice = 35
        val username = "hitler"
        val mockResponse = mockk<CheckoutResponse>(relaxed = true)
        runTest {
            coEvery { dataSource.createOrder(any()) } returns mockResponse
            repository.createOrder(username, mockCart, totalPrice).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.createOrder(any()) }
            }
        }
    }

    @Test
    fun `when create order loading`() {
        val mockCart =
            listOf(
                Cart(
                    id = 1,
                    productId = "asdaaaasdasd",
                    productName = "sdfaasdf",
                    productImgUrl = "sdaafsdfsd",
                    productPrice = 10000.0,
                    itemQuantity = 2,
                    itemNotes = "dfgdsfgsddfg",
                ),
                Cart(
                    id = 2,
                    productId = "asdasssdasd",
                    productName = "sdfsdssf",
                    productImgUrl = "sdfsssdfsd",
                    productPrice = 10000.0,
                    itemQuantity = 2,
                    itemNotes = "dfgdsfgsddfg",
                ),
            )
        val totalPrice = 35
        val username = "hitler"
        val mockResponse = mockk<CheckoutResponse>(relaxed = true)
        runTest {
            coEvery { dataSource.createOrder(any()) } returns mockResponse
            repository.createOrder(username, mockCart, totalPrice).map {
                delay(100)
                it
            }.test {
                delay(110)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { dataSource.createOrder(any()) }
            }
        }
    }

    @Test
    fun `when create order error`() {
        val mockCart =
            listOf(
                Cart(
                    id = 1,
                    productId = "asaadasdasd",
                    productName = "sdfsdf",
                    productImgUrl = "sadfsdfasd",
                    productPrice = 10000.0,
                    itemQuantity = 4,
                    itemNotes = "dfgdsfsdgsddfg",
                ),
                Cart(
                    id = 2,
                    productId = "asaadasdasd",
                    productName = "sdfssdf",
                    productImgUrl = "sdfdsdfsd",
                    productPrice = 10000.0,
                    itemQuantity = 2,
                    itemNotes = "dfgdsfgsddfg",
                ),
            )
        val totalPrice = 35
        val username = "hitler"
        runTest {
            coEvery { dataSource.createOrder(any()) } throws IllegalStateException("Mock error")
            repository.createOrder(username, mockCart, totalPrice).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { dataSource.createOrder(any()) }
            }
        }
    }
}
