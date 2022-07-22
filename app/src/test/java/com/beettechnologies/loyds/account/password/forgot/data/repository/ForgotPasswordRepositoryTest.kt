package com.beettechnologies.loyds.account.password.forgot.data.repository

import com.beettechnologies.loyds.account.password.forgot.data.api.ForgotPasswordApi
import com.beettechnologies.loyds.account.password.forgot.domain.repository.ForgotPasswordRepository
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.data.model.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class ForgotPasswordRepositoryTest {

    private val forgotPasswordApi = mockk<ForgotPasswordApi>()

    private val sut: ForgotPasswordRepository = ForgotPasswordRepositoryImpl(forgotPasswordApi)

    @Test
    fun `forgot password , user credentials, return Success`() = runTest {

        val response = Any()

        coEvery { forgotPasswordApi.resetPassword(any()) } returns Response.success(response)

        val actual = mutableListOf<Resource<Boolean>>()

        sut.resetPassword("dummyEmail")
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, null)
        assertEquals(actual[1].status, Status.SUCCESS)
        assertEquals(actual[1].data, true)
    }

    @Test
    fun `forgot password with no internet , user credentials, return Error`() = runTest {
        coEvery { forgotPasswordApi.resetPassword(any()) } throws UnknownHostException()

        val actual = mutableListOf<Resource<Boolean>>()

        sut.resetPassword("dummyEmail")
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, "No internet connection available!")
        assertEquals(actual[1].status, Status.ERROR)
        assertEquals(actual[1].data, null)
    }
}