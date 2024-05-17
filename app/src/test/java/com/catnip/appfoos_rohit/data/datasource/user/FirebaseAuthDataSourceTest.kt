package com.catnip.appfoos_rohit.data.datasource.user

import app.cash.turbine.test
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.data.repository.UserRepositoryImpl
import com.catnip.appfood_rohit.utils.ResultWrapper
import com.catnip.appfoos_rohit.data.model.User
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FirebaseAuthDataSourceTest {
    @MockK
    private lateinit var dataSource: AuthDataSource
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = UserRepositoryImpl(dataSource)
    }

    @Test
    fun doLogin() {
        val email = "rohit@gmail.com"
        val password = "123123"
        runTest {
            coEvery { dataSource.doLogin(email, password) } returns true
            repository.doLogin(email, password).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.doLogin(email, password) }
            }
        }
    }

    @Test
    fun doRegister() {
        val name = "rohit"
        val email = "rohit@gmail.com"
        val password = "rohit123"
        runTest {
            coEvery { dataSource.doRegister(name, email, password) } returns true
            repository.doRegister(name, email, password).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.doRegister(name, email, password) }
            }
        }
    }

    @Test
    fun updateProfile() {
        runTest {
            coEvery { dataSource.updateProfile(any()) } returns true
            repository.updateProfile().map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.updateProfile(any()) }
            }
        }
    }

    @Test
    fun updatePassword() {
        val password = "123123"
        runTest {
            coEvery { dataSource.updatePassword(password) } returns true
            repository.updatePassword(password).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.updatePassword(password) }
            }
        }
    }

    @Test
    fun updateEmail() {
        val email = "rohit@gmail.com"
        runTest {
            coEvery { dataSource.updateEmail(email) } returns true
            repository.updateEmail(email).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { dataSource.updateEmail(email) }
            }
        }
    }

    @Test
    fun requestChangePasswordByEmail() =
        runTest {
            val result = true

            coEvery { dataSource.requestChangePasswordByEmail() } returns result

            val actual = repository.requestChangePasswordByEmail()
            assertEquals(result, actual)

            coVerify { dataSource.requestChangePasswordByEmail() }
        }

    @Test
    fun doLogout() =
        runTest {
            val result = true

            coEvery { dataSource.doLogout() } returns result

            val actual = repository.doLogout()
            assertEquals(result, actual)

            coVerify { dataSource.doLogout() }
        }

    @Test
    fun isLoggedIn() =
        runTest {
            val result = true

            coEvery { dataSource.isLoggedIn() } returns result

            val actual = repository.isLoggedIn()
            assertEquals(result, actual)

            coVerify { dataSource.isLoggedIn() }
        }

    @Test
    fun getCurrentUser() =
        runTest {
            val user = User("userId", "username", "email")

            coEvery { dataSource.getCurrentUser() } returns user

            val actual = repository.getCurrentUser()
            assertEquals(user, actual)

            coVerify { dataSource.getCurrentUser() }
        }
}
