package com.lcarlosfb.p3.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.toColorInt
import com.lcarlosfb.p3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var mainBinding: ActivityMainBinding

	@SuppressLint("SetTextI18n")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mainBinding = ActivityMainBinding.inflate(layoutInflater)
		val view = mainBinding.root
		setContentView(view)

		mainBinding.btnGenerarNum.setOnClickListener {
			mainBinding.edtAdivinar.setText("")
			mainBinding.tvResultado.setText("")
			val nrInput = mainBinding.edtNumber.text.toString()

			if (nrInput.isEmpty()) {
				mainBinding.tvNumeroGenerado.setText("Rellenar campos vacíos")
				Toast.makeText(applicationContext, "Hay campos vacíos", Toast.LENGTH_SHORT).show()
				mainBinding.tvNumeroGenerado.setTextColor("#FF0000".toColorInt())
			}else if (nrInput.toInt() <= 1){
				mainBinding.tvNumeroGenerado.setText("El número seleccionado debe ser mayor que 1")
				mainBinding.tvNumeroGenerado.setTextColor("#FF0000".toColorInt())
			}else{
				val number = (1..nrInput.toInt()).random()
				mainBinding.tvNumeroGenerado.setText("El número secreto ha sido generado entre: [1 y $nrInput]")
				mainBinding.tvNumeroGenerado.setTextColor("#008000".toColorInt())
				var contador = 0
				mainBinding.edtNumber.isEnabled = false
				mainBinding.btnGenerarNum.isEnabled = false
				mainBinding.edtAdivinar.isEnabled = true
				mainBinding.btnAdivinar.isEnabled = true

				mainBinding.btnAdivinar.setOnClickListener {
					val nrValidate = mainBinding.edtAdivinar.text.toString()

					if (nrValidate.isEmpty()) {
						mainBinding.tvResultado.setText("Rellenar campos vacíos")
						mainBinding.tvResultado.setTextColor("#FF0000".toColorInt())
						Toast.makeText(applicationContext, "Hay campos vacíos", Toast.LENGTH_SHORT).show()
					}else if(nrValidate.toInt() < 1 || nrValidate.toInt() > nrInput.toInt()){
						mainBinding.tvResultado.setTextColor("#FF0000".toColorInt())
						mainBinding.tvResultado.setText("El número ingresado está fuera del rango [1,$nrInput]")
						mainBinding.edtAdivinar.setText("")
					}else if (nrValidate.toInt() < number){
						mainBinding.tvResultado.setTextColor("#FF0000".toColorInt())
						mainBinding.tvResultado.setText("El número secreto es mayor")
						contador += 1
					} else if(nrValidate.toInt() > number){
						mainBinding.tvResultado.setTextColor("#FF0000".toColorInt())
						mainBinding.tvResultado.setText("El número secreto es menor")
						contador += 1
					}else{
						contador += 1
						mainBinding.tvResultado.setText("Adivinaste el número secreto $number, en $contador intentos" )
						mainBinding.tvResultado.setTextColor("#008000".toColorInt())
						mainBinding.btnAdivinar.isEnabled = false
						mainBinding.edtAdivinar.isEnabled = false
						mainBinding.btnReset.isEnabled = true

						mainBinding.btnReset.setOnClickListener {
							mainBinding.edtNumber.isEnabled = true
							mainBinding.btnGenerarNum.isEnabled = true
							mainBinding.edtNumber.setText("")
							mainBinding.tvNumeroGenerado.setTextColor("#FF0000".toColorInt())
							mainBinding.tvNumeroGenerado.setText("El número secreto no se ha generado")
							mainBinding.edtAdivinar.setText("")
							mainBinding.btnAdivinar.isEnabled = false
							mainBinding.tvResultado.setText("")
							mainBinding.btnReset.isEnabled = false
						}
					}
				}
			}
		}
	}
}