package com.beettechnologies.loyds.account.login.data.mapper

import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.google.gson.internal.LinkedHashTreeMap
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(item: LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>?) : UserModel {

        val session = item?.get("session") as LinkedHashTreeMap<String, String>
        val expires = session["expires"]?.toLong()

        val isAlive = expires?.let { exp ->
            val currentTime = Calendar.getInstance().time.time
            val expirationTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(convertLongToTime(exp))?.time
            currentTime < expirationTime ?: 0
        }?: true

        return UserModel(
            id = session["id"],
            userId = session["userId"],
            isAlive = isAlive,
        )
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.format(date)
    }
}