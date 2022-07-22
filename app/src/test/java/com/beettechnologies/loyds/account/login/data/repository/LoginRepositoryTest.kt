package com.beettechnologies.loyds.account.login.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beettechnologies.loyds.CoroutineTestRule
import com.beettechnologies.loyds.account.login.data.api.LoginApi
import com.beettechnologies.loyds.account.login.data.mapper.UserMapper
import com.beettechnologies.loyds.account.login.domain.repository.LoginRepository
import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.data.model.Status
import com.google.gson.internal.LinkedHashTreeMap
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class LoginRepositoryTest {

    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val loginApi = mockk<LoginApi>()

    private val sut: LoginRepository = LoginRepositoryImpl(loginApi, UserMapper())

    @Test
    fun `sign in , bad user credentials, return Error`() = runTest {
        val response = "{\n" +
                "  \"detail\": \"Oops! Something happened.\"\n" +
                "}"

        coEvery {
            loginApi.login(any())
        } returns Response.error(402, response.toResponseBody("application/json".toMediaTypeOrNull()))

        val actual = mutableListOf<Resource<UserModel>>()

        sut.login("dummy", "dummy")
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, "Oops! Something happened.")
        assertEquals(actual[1].status, Status.ERROR)
        assertEquals(actual[1].data, null)
    }

    @Test
    fun `sign in , correct user credentials, return Success`() = runTest {
        val response = LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>()

        val session = LinkedHashTreeMap<String, String>()

        session["expires"] = System.currentTimeMillis().toString()
        session["id"] = "dummyTestId"
        session["userId"] = "dummyTestUserId"

        response["session"] = session

        coEvery { loginApi.login(any()) } returns Response.success(response)

        val actual = mutableListOf<Resource<UserModel>>()

        sut.login("dummy", "dummy")
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, null)
        assertEquals(actual[1].status, Status.SUCCESS)
        assertEquals(actual[1].data?.id, "dummyTestId")
        assertEquals(actual[1].data?.userId, "dummyTestUserId")
        assertEquals(actual[1].data?.isAlive, true)
    }

    @Test
    fun `sign in with no internet , correct user credentials, return Error`() = runTest {

        coEvery { loginApi.login(any()) } throws UnknownHostException()

        val actual = mutableListOf<Resource<UserModel>>()

        sut.login("dummy", "dummy")
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