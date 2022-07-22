package com.beettechnologies.loyds.common.data.model

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest  {

    @Test
    fun `test error message`() {
        val exception = Exception("Oops! Something went wrong")
        val (errorMessage) = ApiResponse.create<Any>(exception)
        assertEquals(errorMessage, "Oops! Something went wrong")
    }

    @Test
    fun `test successful http request`() {
        val apiResponse: ApiResponse.ApiSuccessResponse<Any> = ApiResponse
            .create<Any>(Response.success("success")) as ApiResponse.ApiSuccessResponse<Any>
        assertEquals(apiResponse.body, "success")
    }

    @Test
    fun `test error in http request`() {
        val errorResponse = Response.error<Any>(
            400,
            "{ detail: \"Oops! Something went wrong\", code: \"400\" }".toResponseBody("application/json".toMediaTypeOrNull()))

        val (errorMessage) = ApiResponse.create<Any>(errorResponse) as ApiResponse.ApiErrorResponse<Any>
        assertEquals(errorMessage, "Oops! Something went wrong")
    }
}