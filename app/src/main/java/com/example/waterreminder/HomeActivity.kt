package com.example.waterreminder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.text.InputType
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.content_scrolling.*
import android.provider.AlarmClock.ACTION_SHOW_ALARMS
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        var myPreferenceUltimaAgua = MyPreferenceUltimaAgua(this)
        var myPreferenceUltimaHora = MyPreferenceUltimaHora(this)

        btnConsejos.setOnClickListener{
            val intent = Intent(this, ConsejosActivity::class.java)
            startActivity(intent)
        }
        btnConfig.setOnClickListener{
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }
        btnDatos.setOnClickListener{
            val intent = Intent(this,DatosActivity::class.java)
            startActivity(intent)
        }
        radio250.setOnClickListener {
            radio500.isChecked = false
            radioOtro.isChecked = false
            cajitaDeProgreso.text = null
        }

        radio500.setOnClickListener {
            radio250.isChecked = false
            radioOtro.isChecked = false
            cajitaDeProgreso.text = null
        }

        radioOtro.setOnClickListener {
            radio250.isChecked = false
            radio500.isChecked = false
        }

        cajitaDeProgreso.setOnClickListener{
            radio250.isChecked = false
            radio500.isChecked = false
            radioOtro.isChecked = true
        }

        botonYaTome.setOnClickListener{
            actualiza(1)
        }
    }

    fun actualiza(x:Int){
        val hora = SimpleDateFormat("hh:mm:ss")
        val currentDate = hora.format(Date())

        val myPreferenceMeta = MyPreferenceMeta(this)
        var valorMeta = myPreferenceMeta.getMeta()

        val myPreferenceActual = MyPreferenceActual(this)
        var aguaTomada = myPreferenceActual.getActual()

        var myPreferenceUltimaAgua = MyPreferenceUltimaAgua(this)
        var myPreferenceUltimaHora = MyPreferenceUltimaHora(this)

        val myPreferenceHistorial = MyPreferenceHistorial(this)

        var fechaVieja = myPreferenceHistorial.getHistorial()
        val date: Date = Calendar.getInstance().getTime()
        val df: SimpleDateFormat = SimpleDateFormat("dd-MMM-yyyy")
        val formattedDate: String = df.format(date)
        var fechaNueva = formattedDate

        if(fechaVieja.equals("0")){
            myPreferenceHistorial.setHistorial(formattedDate)
        }
        else {
            fechaNueva = formattedDate
            if (!fechaNueva.equals(fechaVieja)){
                Toast.makeText(this,"Se han reiniciado los datos", Toast.LENGTH_SHORT).show()
                myPreferenceActual.setActual(0)
                aguaTomada = 0
                myPreferenceUltimaAgua.setMeta(0)
                myPreferenceUltimaHora.setMeta("")
                myPreferenceHistorial.setHistorial(fechaNueva)
            }
        }

        if(x == 1) {
            var progreso: Int = 0
            if (radio250.isChecked) {
                progreso = 250
                myPreferenceUltimaAgua.setMeta(250)
            } else if (radio500.isChecked) {
                progreso = 500
                myPreferenceUltimaAgua.setMeta(500)
            } else {
                progreso = cajitaDeProgreso.text.toString().toInt()
                myPreferenceUltimaAgua.setMeta(cajitaDeProgreso.text.toString().toInt())
                cajitaDeProgreso.text = null
            }
            aguaTomada += progreso
            myPreferenceActual.setActual(aguaTomada)
            myPreferenceUltimaHora.setMeta(currentDate)
        }
        textoScroll.text = "Tomaste "+myPreferenceUltimaAgua.getMeta()+"ml de agua a las: "+myPreferenceUltimaHora.getMeta()
        barraProgreso.progress = (aguaTomada * 100 / valorMeta)
        meta.text = "$aguaTomada / $valorMeta"
    }

    override fun onResume() {
        super.onResume()
        actualiza(0)
    }
}
