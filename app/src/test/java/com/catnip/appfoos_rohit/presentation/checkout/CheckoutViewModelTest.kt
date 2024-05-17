package com.catnip.appfoos_rohit.presentation.checkout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.appfood_rohit.data.repository.CartRepository
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.presentation.checkout.CheckoutViewModel
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CheckoutViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var cartRepository: CartRepository

    @MockK
    lateinit var userRepository: UserRepository

    @MockK
    lateinit var catalogRepository: ProductRepository

    private lateinit var viewModel: CheckoutViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { cartRepository.getCheckoutData() } returns
            flow {
                emit(
                    ResultWrapper.Success(
                        Triple(
                            listOf(mockk(relaxed = true)), mockk(relaxed = true), 0.0,
                        ),
                    ),
                )
            }

        viewModel =
            spyk(
                CheckoutViewModel(
                    cartRepository,
                    userRepository,
                    catalogRepository,
                ),
            )
    }

    @Test
    fun getCartList() {
        val result = viewModel.cartList.getOrAwaitValue()
        assert(result is ResultWrapper.Success)
        (result as ResultWrapper.Success).payload?.first?.let { assert(it.isNotEmpty()) }
    }

    @Test
    fun getCheckoutData() {
        val result = viewModel.checkoutData.getOrAwaitValue()
        assert(result is ResultWrapper.Success)
        (result as ResultWrapper.Success).payload?.first?.let { assert(it.isNotEmpty()) }
    }

    @Test
    fun checkoutCart() {
        val mockUsername = "saipudin"
        every { userRepository.getCurrentUser()?.fullName } returns mockUsername
        val expected = ResultWrapper.Success(true)
        coEvery { catalogRepository.createOrder(any(), any(), any()) } returns
            flow {
                emit(expected)
            }
        val result = viewModel.checkoutCart().getOrAwaitValue()
        assertEquals(expected, result)
    }

    @Test
    fun clearCart() {
        viewModel.clearCart()
    }

    @Test
    fun isLoggedIn() {
        val isLoggedIn = true
        every { userRepository.isLoggedIn() } returns isLoggedIn
        val result = viewModel.isLoggedIn()
        assertEquals(isLoggedIn, result)
    }
}
