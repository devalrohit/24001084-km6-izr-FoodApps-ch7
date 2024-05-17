package com.catnip.appfoos_rohit.presentation.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.presentation.register.RegisterViewModel
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RegisterViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var repository: UserRepository

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(RegisterViewModel(repository))
    }

    @Test
    fun doRegister() {
        val email = "asdasd@asdasd.com"
        val username = "asdas"
        val password = "asdasd"
        val expected = ResultWrapper.Success(true)

        coEvery { repository.doRegister(username, email, password) } returns
            flow {
                emit(expected)
            }

        val result = viewModel.doRegister(email, username, password).getOrAwaitValue()
        assertEquals(expected, result)
    }
}
