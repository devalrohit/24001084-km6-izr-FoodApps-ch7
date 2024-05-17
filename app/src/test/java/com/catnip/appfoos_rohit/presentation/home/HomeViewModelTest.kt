package com.catnip.appfoos_rohit.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.appfood_rohit.data.repository.CategoryRepository
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.presentation.home.HomeViewModel
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfoos_rohit.data.repository.PreferenceRepository
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var categoryRepository: CategoryRepository

    @MockK
    lateinit var productRepository: ProductRepository

    @MockK
    lateinit var preferenceRepository: PreferenceRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { preferenceRepository.isUsingGridMode() } returns true // Atau false, sesuai dengan nilai yang diharapkan

        viewModel =
            spyk(
                HomeViewModel(
                    categoryRepository,
                    productRepository,
                    preferenceRepository,
                ),
            )
    }

    @Test
    fun isUsingGridMode() {
        val isUsingGridMode = true
        every { preferenceRepository.isUsingGridMode() } returns isUsingGridMode
        viewModel.isUsingGridMode.observeForever {}
        val result = viewModel.isUsingGridMode.value
        assertEquals(isUsingGridMode, result)
    }

    @Test
    fun getListMode() {
        val isUsingGridMode = true
        every { preferenceRepository.isUsingGridMode() } returns isUsingGridMode
        val result = viewModel.getListMode()
        assertEquals(1, result)
    }

    @Test
    fun changeListMode() {
        val initialValue = true
        val newValue = false
        every { preferenceRepository.isUsingGridMode() } returns initialValue
        every { preferenceRepository.setUsingGridMode(any()) } returns Unit

        viewModel.changeListMode()

        verify { preferenceRepository.setUsingGridMode(!initialValue) }
        assertEquals(newValue, viewModel.isUsingGridMode.value)
    }

    @Test
    fun getMenu() {
        every { productRepository.getProducts(any()) } returns
            flow {
                emit(ResultWrapper.Success(listOf(mockk(relaxed = true), mockk(relaxed = true))))
            }
        val result = viewModel.getMenu().getOrAwaitValue()
        assertEquals(2, (result as ResultWrapper.Success).payload?.size)
        verify { productRepository.getProducts(any()) }
    }

    @Test
    fun getCategory() {
        every { categoryRepository.getCategories() } returns
            flow {
                emit(ResultWrapper.Success(listOf(mockk(relaxed = true), mockk(relaxed = true))))
            }
        val result = viewModel.getCategory().getOrAwaitValue()
        assertEquals(2, (result as ResultWrapper.Success).payload?.size)
        verify { categoryRepository.getCategories() }
    }
}
