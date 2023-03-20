package com.lcarlosfb.p3.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lcarlosfb.p3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var mainBinding: ActivityMainBinding
	private lateinit var mainViewModel : MainViewModel

	@SuppressLint("SetTextI18n")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mainBinding = ActivityMainBinding.inflate(layoutInflater)
		mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
		val view = mainBinding.root
		setContentView(view)

		val msgToast = Observer<String>{toastError->
			Toast.makeText(applicationContext,toastError,Toast.LENGTH_SHORT).show()
		}
		mainViewModel.toastError.observe(this, msgToast)

		val msgTvGen = Observer<String>{salidaTvGen->
			mainBinding.tvNumeroGenerado.setText(salidaTvGen)
		}
		mainViewModel.salidaTvGen.observe(this,msgTvGen)

		val colTvGen = Observer<String> {salidaColorGen->
			mainBinding.tvNumeroGenerado.setTextColor(salidaColorGen.toColorInt())
		}
		mainViewModel.salidaColorGen.observe(this, colTvGen)

		val lastViewGen = Observer<Boolean> {lastOption->
			mainBinding.edtNumber.isEnabled = lastOption
			mainBinding.btnGenerarNum.isEnabled = lastOption
		}
		mainViewModel.lastOptionGen.observe(this, lastViewGen)

		val nextViewGen = Observer<Boolean> {nextOption->
			mainBinding.btnAdivinar.isEnabled = nextOption
			mainBinding.edtAdivinar.isEnabled = nextOption
		}
		mainViewModel.nextOptionGen.observe(this, nextViewGen)

		val msgTvAdivinar = Observer<String>{
			mainBinding.tvResultado.setText(it)
		}
		mainViewModel.salidaTvAdivinar.observe(this,msgTvAdivinar)

		val colTvAdivinar = Observer<String> {salidaColorAdivinar->
			mainBinding.tvResultado.setTextColor(salidaColorAdivinar.toColorInt())
		}
		mainViewModel.salidaColorAdivinar.observe(this, colTvAdivinar)

		val lastViewAdivinar = Observer<Boolean> {lastOptionAdivinar->
			mainBinding.edtAdivinar.isEnabled = lastOptionAdivinar
			mainBinding.btnAdivinar.isEnabled = lastOptionAdivinar
		}
		mainViewModel.lastOptionAdivinar.observe(this, lastViewAdivinar)

		val nextViewAdivinar = Observer<Boolean> {nextOptionAdivinar->
			mainBinding.btnReset.isEnabled = nextOptionAdivinar
		}
		mainViewModel.nextOptionAdivinar.observe(this, nextViewAdivinar)



		mainBinding.btnGenerarNum.setOnClickListener {

			val nrInput = mainBinding.edtNumber.text.toString()
			mainViewModel.genNumber(nrInput)

			mainBinding.btnAdivinar.setOnClickListener {
				val nrInputAdivinar = mainBinding.edtAdivinar.text.toString()
				mainViewModel.adivinar(nrInputAdivinar)

				mainBinding.btnReset.setOnClickListener {
					reset()
				}
			}
		}
	}

	@SuppressLint("SetTextI18n")
	private fun reset() {
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

//Punto 3 de la version 1.0 terminado

//Punto 3 de la version 2.0 terminado

