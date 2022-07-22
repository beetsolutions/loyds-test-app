package com.beettechnologies.loyds.account.signup.data.repository

import com.beettechnologies.loyds.account.signup.data.api.SignUpApi
import com.beettechnologies.loyds.account.signup.data.model.AccountModel
import com.beettechnologies.loyds.account.signup.data.model.SignUpResponse
import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
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
class SignUpRepositoryTest {

    private val signUpApi = mockk<SignUpApi>()

    private val sut: SignUpRepository = SignUpRepositoryImpl(signUpApi)

    @Test
    fun `sign up , user credentials, return Success`() = runTest {
        val response = SignUpResponse(AccountModel(
            id = "dummyId",
            username = "dummyUsername",
            email = "dummyEmail",
            verified = true
        ))

        coEvery { signUpApi.signup(any()) } returns Response.success(response)

        val actual = mutableListOf<Resource<Boolean>>()

        sut.signup("dummyEmail", "dummyUsername", "dummyPassword")
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
    fun `sign up with no internet , user credentials, return Error`() = runTest {
        coEvery { signUpApi.signup(any()) } throws UnknownHostException()

        val actual = mutableListOf<Resource<Boolean>>()

        sut.signup("dummyEmail", "dummyUsername", "dummyPassword")
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