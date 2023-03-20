package com.lcarlosfb.p3.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

	val toastError : MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val salidaTvGen : MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val salidaTvAdivinar : MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val salidaColorGen : MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val salidaColorAdivinar : MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val lastOptionGen : MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}
	val nextOptionGen : MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}
	val lastOptionAdivinar : MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}
	val nextOptionAdivinar : MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}

	var number = ""
	var nInput = ""
	var contador = 0

	fun genNumber(nrInput:String){
		if (nrInput.isEmpty()){
			toastError.value = "Rellenar campo vacío"
			salidaTvGen.value = "Rellenar campo vacío"
			salidaColorGen.value = "#FF0000"
		}else if (nrInput.toInt() <= 1){
			salidaTvGen.value = "El número seleccionado debe ser mayor que 1"
			salidaColorGen.value = "#FF0000"
		}else{
			number = (1..nrInput.toInt()).random().toString()
			nInput = nrInput.toInt().toString()
			salidaTvGen.value = "El número secreto ha sido generado entre: [1 y $nrInput]"
			salidaColorGen.value = "#008000"
			lastOptionGen.value = false
			nextOptionGen.value = true
		}
	}

	fun adivinar(nrInputAdivinar:String){

		if(nrInputAdivinar.isEmpty()){
			toastError.value = "Rellenar campo vacío"
			salidaTvAdivinar.value = "Rellenar campo vacío"
			salidaColorAdivinar.value = "#FF0000"
		}else if(nrInputAdivinar.toInt() < 1 || nrInputAdivinar.toInt() > nInput.toInt()){
			salidaTvAdivinar.value = "El número ingresado está fuera del rango [1,$nInput]"
			salidaColorAdivinar.value = "#FF0000"
		}else if (nrInputAdivinar.toInt() < number.toInt()){
			salidaTvAdivinar.value = "El número ingresado es mayor"
			salidaColorAdivinar.value = "#FF0000"
			contador += 1
		}else if (nrInputAdivinar.toInt() > number.toInt()){
			salidaTvAdivinar.value = "El número ingresado es menor"
			salidaColorAdivinar.value = "#FF0000"
			contador += 1
		}else {
			contador += 1
			salidaTvAdivinar.value = "Adivinaste el número secreto $number, en $contador intentos"
			salidaColorAdivinar.value = "#008000"
			lastOptionAdivinar.value = false
			nextOptionAdivinar.value = true
			contador=0 //Correcion contador de intentos
		}

	}
}