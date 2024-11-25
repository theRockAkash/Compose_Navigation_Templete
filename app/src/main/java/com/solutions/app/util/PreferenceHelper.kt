package com.solutions.app.util

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @Created by akash on 23/11/2024.
 * Know more about author on https://akash.cloudemy.in
 */
class PreferenceHelper @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private const val PREF_NAME = "APP Auth Prefs"
        const val USER_NAME_F = "User first Name"
        const val USER_NAME_L = "User last Name"
        const val USER_THUMB = "User thumb"
        const val USER_PHONE = "User phone"
        const val USER_DOB = "User Dob"
        const val USER_EMAIL = "User email"
        const val USER_ID = "User Id"
        const val TOKEN = "TOKEN"
        const val FCM_TOKEN = "FCM_TOKEN"
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)




    fun saveString(s: String, key: String) {
        Log.e("TAG", "saveString: $key ==> $s")
        val editor = sharedPref.edit()
        editor.putString(key, s.trim())
        editor.apply()
    }

    fun saveBoolean(key: String, s: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean(key, s)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, "")
    }

    fun getInt(key: String): Int {
        return sharedPref.getInt(key, -1)
    }

    fun getUserName(): String {
        return sharedPref.getString(USER_NAME_F, "") + " " + sharedPref.getString(USER_NAME_L, "")
    }

    fun logOut() {
        val token = sharedPref.getString(FCM_TOKEN, "not exist")
        val editor = sharedPref.edit()
        editor.clear()
        editor.putString(FCM_TOKEN, token)
        editor.apply()
    }

}