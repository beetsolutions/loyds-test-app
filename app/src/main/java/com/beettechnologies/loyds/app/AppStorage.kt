package com.beettechnologies.loyds.app

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class AppStorage(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE)
    }

    companion object {
        private const val KEY_SHARED_PREF = "loyds-pref"
    }
}