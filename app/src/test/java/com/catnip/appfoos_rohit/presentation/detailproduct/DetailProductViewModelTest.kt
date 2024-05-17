package com.catnip.appfoos_rohit.presentation.detailproduct

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.data.repository.CartRepository
import com.catnip.appfood_rohit.presentation.detailproduct.DetailProductActivity
import com.catnip.appfood_rohit.presentation.detailproduct.DetailProductViewModel
import com.catnip.kokomputer.tools.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailProductViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var cartRepository: CartRepository

    @MockK
    lateinit var intent: Bundle

    private lateinit var viewModel: DetailProductViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        val expectedCatalog =
            Product(
                "1",
                "das_reich",
                "himmler",
                35000.0,
                "Heil fuhrer",
                "Reichstag",
                "sdfsdfsdfdf",
            )
        every { intent.getParcelable<Product>(DetailProductActivity.EXTRA_PRODUCT) } returns expectedCatalog

        viewModel =
            spyk(
                DetailProductViewModel(
                    intent, cartRepository,
                ),
            )
    }

    @Test
    fun getMenuCountLiveData() {
    }

    @Test
    fun getPriceLiveData() {
    }

    @Test
    fun addItem() {
    }

    @Test
    fun minusItem() {
    }

    @Test
    fun addToCart() {
    }
}
