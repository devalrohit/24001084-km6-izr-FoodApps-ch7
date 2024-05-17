package com.catnip.appfoos_rohit.data.repository

import com.example.foodapp.data.datasource.preference.PreferenceDataSource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PrefRepositoryImplTest {
    @MockK
    lateinit var dataSource: PreferenceDataSource
    private lateinit var repository: PreferenceRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = PreferenceRepositoryImpl(dataSource)
    }

    @Test
    fun isUsingGridMode() {
        runTest {
            every { repository.isUsingGridMode() } returns true
            val result = dataSource.isUsingGridMode()
            verify { repository.isUsingGridMode() }
            assertEquals(true, result)
        }
    }

    @Test
    fun setUsingGridMode() {
        runTest {
            val seUsingGridMode = true
            every { repository.setUsingGridMode(seUsingGridMode) } returns Unit
            val result = dataSource.setUsingGridMode(seUsingGridMode)
            verify { repository.setUsingGridMode(seUsingGridMode) }
            assertEquals(Unit, result)
        }
    }
}
