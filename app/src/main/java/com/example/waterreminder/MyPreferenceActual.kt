package com.example.waterreminder

import android.content.Context

class MyPreferenceActual (context: Context){
    val PREFERENCE_NAME = "current"
    val PREFERENCE_LOGIN = "aguaTomada"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getActual() : Int{
        return preference.getInt(PREFERENCE_LOGIN,0)
    }
    fun setActual(count:Int){
        val editor = preference.edit()
        editor.putInt(PREFERENCE_LOGIN,count)
        editor.apply()
    }
}