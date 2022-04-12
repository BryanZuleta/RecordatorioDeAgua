package com.example.waterreminder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_bienvenida.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bienvenida)
        bienvenidabtn.setOnClickListener{
            val intent = Intent(this, DatosActivity::class.java)
            startActivity(intent)
        }
    }

    fun verifica(){
        val myPreference = MyPreferenceBienvenida(this)
        var login = myPreference.getLogin()

        if (!login){
            login = true
            myPreference.setLogin(login)
        }
        else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        verifica()
    }
}
