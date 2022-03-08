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
}
