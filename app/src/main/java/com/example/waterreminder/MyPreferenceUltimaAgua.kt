package com.example.waterreminder

import android.content.Context

class MyPreferenceUltimaAgua(context: Context) {
    val PREFERENCE_NAME = "ultima"
    val PREFERENCE_LOGIN = "agua"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getMeta() : Int{
        return preference.getInt(PREFERENCE_LOGIN,0)
    }
    fun setMeta(count:Int){
        val editor = preference.edit()
        editor.putInt(PREFERENCE_LOGIN,count)
        editor.apply()
    }
}