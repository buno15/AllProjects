package com.bunooboi.composesample

import android.content.SharedPreferences

interface PreferencesRepository {
    fun getString(key: String): String?
    fun putString(key: String, value: String)
    // 他のメソッドも追加できます
}

class SharedPreferencesRepository(private val sharedPreferences: SharedPreferences) : PreferencesRepository {
    override fun getString(key: String): String? = sharedPreferences.getString(key, null)

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}
