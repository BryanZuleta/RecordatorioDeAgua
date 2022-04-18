package com.example.waterreminder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_consejos.*

class ConsejosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_consejos)

        supportActionBar!!.title = "Volver"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
