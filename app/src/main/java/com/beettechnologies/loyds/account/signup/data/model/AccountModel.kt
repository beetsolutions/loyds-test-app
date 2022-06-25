package com.beettechnologies.loyds.account.signup.data.model

data class AccountModel(
    val id: String,
    val username: String,
    val email: String,
    val verified: Boolean
)