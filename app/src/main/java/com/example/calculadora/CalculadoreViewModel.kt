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
        return modeloCalculadora.historialOperaciones(valorR,opcionAppend)
    }
}