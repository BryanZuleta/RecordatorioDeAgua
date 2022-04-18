package com.example.waterreminder

import android.content.Context

class MyPreferenceBienvenida (context: Context){
    val PREFERENCE_NAME = "bienvenida"
    val PREFERENCE_LOGIN = "login"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getLogin() : Boolean{
        return preference.getBoolean(PREFERENCE_LOGIN,false)
    }
    fun setLogin(count:Boolean){
        val editor = preference.edit()
        editor.putBoolean(PREFERENCE_LOGIN,count)
        editor.apply()
    }
}