package com.example.waterreminder

import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_alarma.*
import java.text.SimpleDateFormat
import java.util.*

class AlarmaActivity : AppCompatActivity() {
    var timeFormat = SimpleDateFormat("hh:mm a",Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_alarma)

        buttonAlarm.setOnClickListener{
            escojeAlarma()
        }
    }
    fun escojeAlarma()  {
        val now = Calendar.getInstance()
        val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
            selectedTime.set(Calendar.MINUTE, minute)
            Toast.makeText(this,hourOfDay, Toast.LENGTH_SHORT).show()
            creaAlarma(hourOfDay,minute)
        },now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
        timePicker.show()

    }

    fun creaAlarma(hora: Int,minuto:Int){
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Tomar agua")
        intent.putExtra(AlarmClock.EXTRA_HOUR, hora)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minuto)

        startActivity(intent)
    }
}
