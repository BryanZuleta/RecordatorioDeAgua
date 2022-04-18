package com.example.waterreminder

import android.content.Context
import android.os.Build
import java.time.LocalDate
import java.util.*

class MyPreferenceHistorial (context: Context) {
    val preferenceName = "SharedPreferenceHistorial"
    val preferenceDate = "LoginDate"

    val preference = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun getHistorial(): String? {
        return preference.getString(preferenceDate, "0")
    }

    fun setHistorial(count: String?) {
        val editor = preference.edit()
        editor.putString(preferenceDate, count)
        editor.apply()
    }
}