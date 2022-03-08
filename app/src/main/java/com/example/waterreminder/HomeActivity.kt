package com.example.waterreminder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.text.InputType
import kotlinx.android.synthetic.main.fragment_home.*
import android.provider.AlarmClock.ACTION_SHOW_ALARMS
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
    }
}