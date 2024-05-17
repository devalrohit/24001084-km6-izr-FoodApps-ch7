package com.catnip.appfoos_rohit.data.repository

import app.cash.turbine.test
import com.catnip.appfood_rohit.data.datasource.cart.CartDataSource
import com.catnip.appfood_rohit.data.model.Cart
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.data.repository.CartRepository
import com.catnip.appfood_rohit.data.repository.CartRepositoryImpl
import com.catnip.appfood_rohit.data.source.local.database.entity.CartEntity
import com.catnip.appfood_rohit.utils.ResultWrapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CartRepositoryImplTest {
    @MockK
    lateinit var ds: CartDataSource

    private lateinit var repo: CartRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CartRepositoryImpl(ds)
    }

    @Test
    fun getUserCartData_success() {
        val entity1 =
            CartEntity(
                id = 1,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 8000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        val entity2 =
            CartEntity(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        val mockList = listOf(entity1, entity2)
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getUserCartData().map {
                delay(100)
                it
            }.test {
                delay(2201)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                assertEquals(mockList.size, actualData.payload?.first?.size)
                assertEquals(54000.0, actualData.payload?.second)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getUserCartData_loading() {
        val mockList = emptyList<CartEntity>()
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getUserCartData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Empty)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getUserCartData_error() {
       /* val exception = Exception("Error occurred")
        val mockFlow = flow<List<CartEntity>> { throw exception }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getUserCartData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                verify { ds.getAllCarts() }
            }
        }*/
    }

    @Test
    fun getUserCartData_empty() {
        val mockList = emptyList<CartEntity>()
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getUserCartData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Empty)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getCheckoutData_success() {
       /* val entity1 = mockk<CartEntity>()
        val entity2 = mockk<CartEntity>()
        val mockList = listOf(entity1, entity2)
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getCheckoutData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                verify { ds.getAllCarts() }
            }
        }*/
    }

    @Test
    fun getCheckoutData_error() {
       /* val exception = Exception("Error occurred")
        val mockFlow = flow<List<CartEntity>> { throw exception }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getCheckoutData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
                verify { ds.getAllCarts() }
            }
        }*/
    }

    @Test
    fun getCheckoutData_loading() {
        val mockList = emptyList<CartEntity>()
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getCheckoutData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Empty)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun getCheckoutData_empty() {
        val mockList = emptyList<CartEntity>()
        val mockFlow = flow { emit(mockList) }
        every { ds.getAllCarts() } returns mockFlow
        runTest {
            repo.getCheckoutData().test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Empty)
                verify { ds.getAllCarts() }
            }
        }
    }

    @Test
    fun createCart_success() {
        val mockProduct = mockk<Product>(relaxed = true)
        every { mockProduct.id } returns "123"
        coEvery { ds.insertCart(any()) } returns 1
        runTest {
            repo.createCart(mockProduct, 3, "afawfawf")
                .map {
                    delay(100)
                    it
                }.test {
                    delay(2201)
                    val actualData = expectMostRecentItem()
                    assertTrue(actualData is ResultWrapper.Success)
                    assertEquals(true, actualData.payload)
                    coVerify { ds.insertCart(any()) }
                }
        }
    }

    @Test
    fun createCart_loading() {
        val mockProduct = mockk<Product>(relaxed = true)
        every { mockProduct.id } returns "123"
        coEvery { ds.insertCart(any()) } returns 1
        runTest {
            repo.createCart(mockProduct, 3, "afawfawf").test {
                val loadingItem = expectMostRecentItem()
                assertTrue(loadingItem is ResultWrapper.Loading)
                advanceTimeBy(2001)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify { ds.insertCart(any()) }
            }
        }
    }

    @Test
    fun createCart_error_processing() {
    }

    @Test
    fun createCart_error_no_product_id() {
        val mockProduct = mockk<Product>(relaxed = true)
        every { mockProduct.id } returns null
        runTest {
            repo.createCart(mockProduct, 3, "afawfawf").test {
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Error)
            }
        }
    }

    @Test
    fun decreaseCart_when_quantity_more_than_0() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.deleteCart(any()) } returns 1
        coEvery { ds.updateCart(any()) } returns 1
        runTest {
            repo.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(210)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 0) { ds.deleteCart(any()) }
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun decreaseCart_when_quantity_less_than_1() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 1,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.deleteCart(any()) } returns 1
        coEvery { ds.updateCart(any()) } returns 1
        runTest {
            repo.decreaseCart(mockCart).test {
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
                coVerify(atLeast = 0) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun increaseCart() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.updateCart(any()) } returns 1
        runTest {
            repo.increaseCart(mockCart).test {
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun setCartNotes() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.updateCart(any()) } returns 1
        runTest {
            repo.decreaseCart(mockCart).map {
                delay(100)
                it
            }.test {
                delay(210)
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.updateCart(any()) }
            }
        }
    }

    @Test
    fun deleteCart() {
        val mockCart =
            Cart(
                id = 2,
                productId = "afwwfawf",
                productName = "awfawf",
                productImgUrl = "awfawfafawf",
                productPrice = 10000.0,
                itemQuantity = 3,
                itemNotes = "awfwafawfawfafwafa",
            )
        coEvery { ds.deleteCart(any()) } returns 1
        runTest {
            repo.deleteCart(mockCart).test {
                val actualData = expectMostRecentItem()
                assertTrue(actualData is ResultWrapper.Success)
                coVerify(atLeast = 1) { ds.deleteCart(any()) }
            }
        }
    }

    @Test
    fun deleteAllCart() {
        coEvery { ds.deleteAll() } just runs
        runTest {
            repo.deleteAll()
            coVerify { ds.deleteAll() }
        }
    }
}
