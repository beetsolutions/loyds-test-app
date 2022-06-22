package com.beettechnologies.loyds.account.login.data.model

data class LoginResponse(
    val id: String,
    val username: String,
    val email: String,
    val verified: Boolean
)