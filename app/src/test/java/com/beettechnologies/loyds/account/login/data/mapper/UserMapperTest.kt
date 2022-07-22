package com.beettechnologies.loyds.account.login.data.mapper

import com.google.gson.internal.LinkedHashTreeMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class UserMapperTest {

    private val sut = UserMapper()

    @Test
    fun `map response , from login, return UserModel`() {

        val response = LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>()

        val session = LinkedHashTreeMap<String, String>()

        session["expires"] = "1623699579"
        session["id"] = "dummyTestId"
        session["userId"] = "dummyTestUserId"

        response["session"] = session

        val user = sut.map(response)

        assertEquals(user.isAlive, false)
        assertEquals(user.id, "dummyTestId")
        assertEquals(user.userId, "dummyTestUserId")
    }
}