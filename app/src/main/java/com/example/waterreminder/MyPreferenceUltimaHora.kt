package com.example.waterreminder

import android.content.Context

class MyPreferenceUltimaHora(context: Context) {
    val PREFERENCE_NAME = "ultimaHora"
    val PREFERENCE_LOGIN = "agua11"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getMeta() : String?{
        return preference.getString(PREFERENCE_LOGIN,"")
    }
    fun setMeta(count:String?){
        val editor = preference.edit()
        editor.putString(PREFERENCE_LOGIN,count)
        editor.apply()
    }
}