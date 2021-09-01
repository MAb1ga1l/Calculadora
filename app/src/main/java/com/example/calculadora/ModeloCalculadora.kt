package com.example.calculadora


import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class ModeloCalculadora {

    private var numeroUno : Double = 0.0 // Primer número ingresado para la operación
    private var numeroDos : Double= 0.0 // Segundo número ingresado para la operación
    private var operacion = "" //Operación a realizar
    private var puntoActivo = false
    private var valorMemoria : Double = 0.0 //Valor guardado en memoria para operaciones
    private var historial :String = "" //Historial que se mostrará en el display de Operaciones
    private var historialvalido = 0 //variable para distinguir el número de elementos en el historial

    //función para recibir operandos
    fun setOperando(unOperando : Double){
        numeroUno = unOperando
        historialvalido += 1
        puntoActivo = false
    }


    // ejecutar operaciónes basicas
    fun ejecutaOperacionBasica(operacionR : String): Double {
        historialvalido += 1
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
            "=" -> historialvalido = 3
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

    //función para ejecutar las operaciones relacionadas a la memoria
    fun ejecutaOperacionMemoria(operacionR: String,unOperando: Double):Double{
        var resultado = 0.0

        when(operacionR){
            "Store" -> {valorMemoria = unOperando
                        resultado = valorMemoria}
            "Recall"-> resultado = valorMemoria
            "Mem +" -> resultado = unOperando + valorMemoria
            "Mem -" -> resultado = unOperando - valorMemoria
            "MC" -> {valorMemoria = 0.0
                    resultado = unOperando}
        }
        return resultado
    }

    //función para limpiar todas las variables
    fun limpiaTodo(){
        numeroUno = 0.0
        numeroDos = 0.0
        operacion = ""
        puntoActivo = false
        valorMemoria = 0.0
        historial=""
        historialvalido =0
    }

    //función para regresar el String con el historial de operaciones
    fun historialOperaciones(valorR : String,opcionAppend:String) : String{
        when (opcionAppend){
            "numero" -> agregarNumeroAHistorial(valorR)
            "opI" -> agregarOperacionIndividual(valorR)
            "opC" -> agregarOperacionComplementaria(valorR)
        }
        return historial
    }

    private fun agregarNumeroAHistorial(valorR: String){
        if(historialvalido==3){
            //En caso de ser necesario borrar el historial
            historial = valorR
            historialvalido = 0
        }else{
            historial = historial.plus(valorR)
        }
    }

    private fun agregarOperacionIndividual(valorR: String){

    }

    private fun agregarOperacionComplementaria(valorR: String){

    }
}
