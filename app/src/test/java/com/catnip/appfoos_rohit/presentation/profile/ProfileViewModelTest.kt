package com.catnip.appfoos_rohit.presentation.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.presentation.profile.ProfileViewModel
import com.catnip.appfoos_rohit.data.model.User
import com.catnip.kokomputer.tools.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProfileViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    lateinit var repository: UserRepository

    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(ProfileViewModel(repository))
    }

    @Test
    fun getProfileData() {
     /*   val profi =
            Profile(
                name = "Muhammad Izroil",
                nomor = "085159940924",
                email = "muhammadizroil22@gmail.com",
                profileImg = "https://github.com/devalrohit/AppFoodch4_assets/blob/main/ic_profile.png?raw=true",
            )
        every { repository.updateProfile() } returns user
        val result = viewModel.updateProfile()
        assertEquals(profi, result)*/
    }

    @Test
    fun isEditMode() {
    }

    @Test
    fun changeEditMode() {
/*        every { repository.requestChangePasswordByEmail() } returns true
        viewModel.changeEditMode()
        verify { repository.requestChangePasswordByEmail() }*/
    }

    @Test
    fun isLoggedIn() {
        every { repository.isLoggedIn() } returns true
        val result = viewModel.isLoggedIn()
        assertTrue(result)
    }

    @Test
    fun doLogout() {
        every { repository.doLogout() } returns true
        viewModel.doLogout()
        verify { repository.doLogout() }
    }

    @Test
    fun getCurrentUser() {
        val user =
            User(
                "1",
                "Hitler",
                "hitler@gmail.com",
            )
        every { repository.getCurrentUser() } returns user
        val result = viewModel.getCurrentUser()
        assertEquals(user, result)
    }
}
