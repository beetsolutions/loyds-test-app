package com.beettechnologies.loyds.account.signup.data.model

data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String
)