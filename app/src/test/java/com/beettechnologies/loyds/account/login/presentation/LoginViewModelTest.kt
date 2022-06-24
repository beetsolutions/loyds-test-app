package com.beettechnologies.loyds.account.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beettechnologies.loyds.CoroutineTestRule
import com.beettechnologies.loyds.account.login.domain.interactor.LoginUseCase
import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val loginUseCase = spyk(LoginUseCase(mockk()))
    private val sut = spyk(LoginViewModel(loginUseCase))

    @Test
    fun `sign in , bad user credentials, return Error`() = runTest {
        coEvery { loginUseCase(any()) } returns flow { emit(Resource.error(ERROR_MESSAGE,null)) }
        sut.login(DUMMY_USERNAME, DUMMY_PASSWORD)
        advanceUntilIdle()
        assertEquals(sut.errorMessage.value, ERROR_MESSAGE)
        assertEquals(sut.hasError.value, true)
        assertEquals(sut.isLoading.value, false)
        coVerify { loginUseCase(any()) }
    }

    @Test
    fun `sign in , correct user credentials, return Success`() = runTest {
        coEvery { loginUseCase(any()) } returns flow { emit(Resource.success(USER)) }
        sut.login(DUMMY_USERNAME, DUMMY_PASSWORD)
        advanceUntilIdle()
        assertEquals(sut.errorMessage.value, null)
        assertEquals(sut.hasError.value, false)
        assertEquals(sut.isLoading.value, false)
        coVerify { loginUseCase(any()) }
    }

    @Test
    fun `sign in , correct user credentials, return Loading`() = runTest {
        coEvery { loginUseCase(any()) } returns flow { emit(Resource.loading(null)) }
        sut.login(DUMMY_USERNAME, DUMMY_PASSWORD)
        advanceUntilIdle()
        assertEquals(sut.errorMessage.value, null)
        assertEquals(sut.hasError.value, false)
        assertEquals(sut.isLoading.value, true)
        coVerify { loginUseCase(any()) }
    }

    companion object {
        private const val DUMMY_USERNAME = "dummy_username"
        private const val DUMMY_PASSWORD = "dummy_password"
        private const val ERROR_MESSAGE = "Username or password is incorrect."
        private val USER = UserModel(id = "dummy_id", isAlive = true, userId = "dummy_user_id")
    }
}