package com.example.practicanavigationdrawer.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString("authToken", token).apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString("authToken", null)
    }

    fun clearAuthToken() {
        sharedPreferences.edit().remove("authToken").apply()
    }
}
