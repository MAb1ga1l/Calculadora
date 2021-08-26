package com.example.calculadora


import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class ModeloCalculadora {

    private var numeroUno : Double = 0.0 // Primer numero ingresado para la operación
    private var numeroDos : Double= 0.0 // Segundo numero ingresado para la operación
    private var operacion = "" //Operación a realizar
    private var puntoActivo = false

    //función para recibir operandos
    fun setOperando(unOperando : Double){
        numeroUno = unOperando
        puntoActivo = false
    }


    // ejecutar operaciónes basicas
    fun ejecutaOperacionBasica(operacionR : String): Double {
        ejecutaOperacionEnEspera()

        operacion =  operacionR
        numeroDos = numeroUno
        return if (numeroUno.isNaN() || numeroUno.isInfinite()){
            0.0
        }else{
            numeroUno
        }
    }

    private fun ejecutaOperacionEnEspera(){
        when(operacion){
            "+" -> numeroUno += numeroDos
            "-" -> numeroUno = numeroDos - numeroUno
            "*" -> numeroUno *= numeroDos
            "/" -> numeroUno = numeroDos/numeroUno
            "x ^ n" -> numeroUno = numeroDos.pow(numeroUno)
            "root n" -> numeroUno = numeroDos.pow(1/numeroUno)
        }
    }

    //ejecutar operación compleja solo con un número
    fun ejecutaOperacionCompleja(operacionR: String):Double{
        when(operacionR){
            "+ / -" -> numeroUno *= -1
            "cos" -> numeroUno = cos(numeroUno)
            "sin" -> numeroUno = sin(numeroUno)
            "sqrt" -> numeroUno = sqrt(numeroUno)
            "1 / x"-> numeroUno = 1/numeroUno
            "10 ^ x" -> numeroUno = 10.0.pow(numeroUno)
        }
        return if (numeroUno.isNaN() || numeroUno.isInfinite()){
            0.0
        }else{
            numeroUno
        }
    }

    //función para validar que el punto decimal es valido
    fun validaPunto(operando:String) : String{
        return if(puntoActivo){
            //avisar que no es un número valido
            operando
        }else{
            puntoActivo = true
            operando.plus(".")
        }

    }

}