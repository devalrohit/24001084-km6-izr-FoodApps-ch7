package com.catnip.appfoos_rohit.data.repository

import app.cash.turbine.test
import com.catnip.appfood_rohit.data.datasource.category.CategoryDataSource
import com.catnip.appfood_rohit.data.repository.CategoryRepository
import com.catnip.appfood_rohit.data.repository.CategoryRepositoryImpl
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfoos_rohit.data.source.network.model.category.CategoriesResponse
import com.catnip.appfoos_rohit.data.source.network.model.category.CategoryItemResponse
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

class CategoryRepositoryImplTest {
    @MockK
    lateinit var ds: CategoryDataSource
    private lateinit var repo: CategoryRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CategoryRepositoryImpl(ds)
    }

    @Test
    fun getCategories_loading() {
        val c1 =
            CategoryItemResponse(
                id = "123",
                nama = "asdasd",
                imageUrl = "adsad",
                slug = "wdqdq",
            )
        val c2 =
            CategoryItemResponse(
                id = "123s",
                nama = "assdasd",
                imageUrl = "adssad",
                slug = "wdqdqaa",
            )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<CategoriesResponse>()
        every { mockResponse.data } returns mockListCategory
        runTest {
            coEvery { ds.getCategories() } returns mockResponse
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(110)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { ds.getCategories() }
            }
        }
    }

    @Test
    fun getCategories_success() {
        val c1 =
            CategoryItemResponse(
                id = "123",
                nama = "asdassd",
                imageUrl = "adssad",
                slug = "wdqdsq",
            )
        val c2 =
            CategoryItemResponse(
                id = "123s",
                nama = "asssdasd",
                imageUrl = "asdssad",
                slug = "wdqdsqaa",
            )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<CategoriesResponse>()
        every { mockResponse.data } returns mockListCategory
        runTest {
            coEvery { ds.getCategories() } returns mockResponse
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getCategories() }
            }
        }
    }

    @Test
    fun getCategories_error() {
        runTest {
            coEvery { ds.getCategories() } throws IllegalStateException("Mock Error")
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(310)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { ds.getCategories() }
            }
        }
    }

    @Test
    fun getCategories_empty() {
        val mockListCategory = listOf<CategoryItemResponse>()
        val mockResponse = mockk<CategoriesResponse>()
        every { mockResponse.data } returns mockListCategory
        runTest {
            coEvery { ds.getCategories() } returns mockResponse
            repo.getCategories().map {
                delay(100)
                it
            }.test {
                delay(410)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Empty)
                coVerify { ds.getCategories() }
            }
        }
    }
}
