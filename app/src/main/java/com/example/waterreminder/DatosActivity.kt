package com.example.waterreminder

import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_datos.*
import java.text.SimpleDateFormat
import java.util.*

class DatosActivity : AppCompatActivity() {

    var timeFormat = SimpleDateFormat("hh:mm a",Locale.US)
    var horaLevanta: Int=0
    var horaDuerme: Int=0
    var peso: Int=0
    var aguaPorBeber: Int=0
    var nroAlarmas: Int=0
    var exito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_datos)

        btnDatos.setOnClickListener {
            if(campoNroAlarmas.text.toString().equals("")){
                nroAlarmas = 0
                Toast.makeText(this,"¡Debe ingresar almenos una alarma!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                nroAlarmas = campoNroAlarmas.text.toString().toInt()
                if(nroAlarmas <10){
                    if(horaDuerme == 0 || horaLevanta == 0){
                        Toast.makeText(this,"¡Debe Ingresar una hora de dormir y una hora de levantar!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    programaAlarma()
                }else{
                    Toast.makeText(this,"¡Demasiadas alarmas, debe ingresar menos de 10 alarmas!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            if(campoKg.text.toString().equals("")){
                Toast.makeText(this,"¡Debe ingresar el peso!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                peso = campoKg.text.toString().toInt();
                if(peso<500){
                    exito = true
                    calculaObjetivoIngesta(peso)
                    val myPreference = MyPreferenceMeta(this)
                    myPreference.setMeta(aguaPorBeber)
                }else{
                    Toast.makeText(this,campoKg.text.toString()+"kg ¿Enserio?, ¡no te creo!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }



        }

        btnContinuar.setOnClickListener{
            if(!exito){
                Toast.makeText(this,"¡Faltan datos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        campoHoraLevanta.setOnClickListener{
            escogeHoraLevanta()
        }
        campoHoraDuerme.setOnClickListener{
            escogeHoraDuerme()
        }
    }

    fun escogeHoraLevanta() {
        val now = Calendar.getInstance()
        val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
            selectedTime.set(Calendar.MINUTE, minute)
            Toast.makeText(this,"Hora: " + timeFormat.format(selectedTime.time), Toast.LENGTH_SHORT).show()
            horaLevanta = hourOfDay
        },now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
        timePicker.show()
    }

    fun escogeHoraDuerme() {
        val now = Calendar.getInstance()
        val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
            selectedTime.set(Calendar.MINUTE, minute)
            Toast.makeText(this,"Hora: " + timeFormat.format(selectedTime.time), Toast.LENGTH_SHORT).show()
            horaDuerme = hourOfDay
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

    fun programaAlarma(){
        val horaDespierto: Int = horaDuerme - horaLevanta
        val divisionAlarma: Int = horaDespierto / nroAlarmas;
        Toast.makeText(this,horaDespierto.toString(), Toast.LENGTH_SHORT).show()
        var tiempoAlarma: Int = horaLevanta;
        for (x in 0 until nroAlarmas){
            tiempoAlarma += divisionAlarma
            creaAlarma(tiempoAlarma, 0)
        }
    }

    fun calculaObjetivoIngesta(peso: Int){
        aguaPorBeber = peso*35;
    }
}
