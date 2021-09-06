package com.example.calculadora

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "CalculadoraViewModel"
//el punto de esta clase no es sustituir al modelo va más hacía guardar un estado especifico
//Esta clase va a estar en medio del ViewModel y el controlador
class CalculadoreViewModel : ViewModel(){

    private val modeloCalculadora = ModeloCalculadora()
    var resultado = 0.0
    var recibeNum = false
    private var historial = ""
    private var historialValido = 0
    private var numeros = arrayOf("","")
    private var operacionHistorial = ""

    init {
        Log.d(TAG,"Instancia de ViewModel creada")
    }

    fun setOperando(unOperando:Double){
        //Solo será un intermediario con el ModeloCalculadora
        modeloCalculadora.setOperando(unOperando)
    }

    fun ejecutaOperacionBasica(operacionRecibida:String):Double{
        return  modeloCalculadora.ejecutaOperacionBasica(operacionRecibida)
    }

    fun validaPunto(operando:String):String{
        return  modeloCalculadora.validaPunto(operando)
    }

    fun ejecutaOperacionCompleja(operacionRecibida: String):Double{
        return modeloCalculadora.ejecutaOperacionCompleja(operacionRecibida)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"Instancia de ViewModel esta por destruirse")
    }

    fun ejecutaOperacionMemoria(operacionR: String,unOperando: Double):Double {
        return modeloCalculadora.ejecutaOperacionMemoria(operacionR,unOperando)
    }

    fun limpiaTodo(){
        modeloCalculadora.limpiaTodo()
    }

    fun historialOperaciones(valorR : String,opcionAppend:String) : String{
        return modeloCalculadora.historialOperaciones(valorR,opcionAppend,recibeNum)
    }

    fun guardarEstadoHistorial(){
        //Para poder guardar un correcto estado y una buena interacción con el historial
        //al momento de rotar el teléfono se guardan las variables necesarias
        historial = modeloCalculadora.historial
        historialValido = modeloCalculadora.historialvalido
        numeros = modeloCalculadora.numeros
        operacionHistorial = modeloCalculadora.operacionMostrada
    }

    fun retomarEstadoHistorial():String{
        //Para poder retomar el historial
        modeloCalculadora.historial = historial
        modeloCalculadora.historialvalido = historialValido
        modeloCalculadora.numeros = numeros
        modeloCalculadora.operacionMostrada = operacionHistorial
        return historial
    }

}