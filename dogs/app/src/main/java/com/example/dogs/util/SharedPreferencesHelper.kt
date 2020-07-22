package com.example.dogs.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {

    companion object {

        private const val PRE_TIME = "Pref time"
        private var prefs: SharedPreferences? = null

        @Volatile private var instance: SharedPreferencesHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    fun saveUpdateTime(time: Long) {
        prefs?.edit(commit = true) {
            putLong(PRE_TIME, time)
        }
    }

    fun getUpdateTime() = prefs?.getLong(PRE_TIME, 0)

    fun getCacheDuration() = prefs?.getString("pref_cache_duration", "")
}