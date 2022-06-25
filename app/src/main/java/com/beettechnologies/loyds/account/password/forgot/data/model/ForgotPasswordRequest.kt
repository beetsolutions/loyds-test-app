package com.beettechnologies.loyds.account.password.forgot.data.model

data class ForgotPasswordRequest(
    val email: String,
    val fromName: String = "Test App",
    val subject: String = "Password reset",
    val textContent: String = "Hi there,\n click here to reset your password: dummy.com/reset/code?=\$code"
)