package com.example.calculadora



import kotlin.math.*

//private const val TAG = "ModeloCalculadora"
class ModeloCalculadora {

    private var numeroUno : Double = 0.0 // Primer número ingresado para la operación
    private var numeroDos : Double= 0.0 // Segundo número ingresado para la operación
    private var operacion = "" //Operación a realizar
    private var puntoActivo = false
    private var valorMemoria : Double = 0.0 //Valor guardado en memoria para operaciones
    var historial :String = "" //Historial que se mostrará en el display de Operaciones
    var historialvalido = 0 //variable para ver en que número del arreglo estamos
    var numeros = arrayOf("","") // números que se van a presentar en el historial
    var operacionMostrada = "" // operación que se mostrara en el historial

    //función para recibir operandos
    fun setOperando(unOperando : Double){
        numeroUno = unOperando
        puntoActivo = false
        numeros[historialvalido] = unOperando.toString()
        historialvalido += 1
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
            historialOperaciones(".","numero",true)
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
        historialvalido = 0
        numeros[0] = ""
        numeros[1] = ""
        operacionMostrada = ""
    }

    //función para regresar el String con el historial de operaciones
    fun historialOperaciones(valorR : String,opcionAppend:String,escribiendoNum :Boolean ) : String{
        if(historialvalido>=2){
            historial = ""
            historialvalido = 1
            numeros[0] = valorR
            numeros[1] = ""
        }
        when (opcionAppend){
            "numero" -> {if (escribiendoNum){ agregarNumeroAHistorial(valorR)}}
            "opI" -> {agregarOperacionIndividual(valorR)}
            "opC" -> agregarOperacionComplementaria(valorR)
        }

        if(historialvalido==0){
            historial = ""
            historial = historial.plus(numeros[historialvalido])
        }
        if(historialvalido == 1){
            historial = ""
            historial = historial.plus(numeros[0])
            historial = historial.plus(operacionMostrada)
            historial = historial.plus(numeros[historialvalido])
        }

        return historial
    }

    private fun agregarNumeroAHistorial(valorR: String){
        numeros[historialvalido] = numeros[historialvalido].plus(valorR)
    }

    private fun agregarOperacionIndividual(valorR: String){
        if(valorR == "="){
            historialvalido = 1
            numeros[0] = numeroDos.toString()
            numeros[1] = ""
            operacionMostrada = ""
        }else{
            operacionMostrada = valorR
        }
    }

    private fun agregarOperacionComplementaria(valorR: String){
        var aux = " "
        when(valorR){
            "+ / -" -> aux = (numeros[historialvalido].toDouble() * -1).toString()
            "cos" ->{ aux = aux.plus(" cos(")
                      aux = aux.plus(numeros[historialvalido])
                      aux = aux.plus(")") }
            "sin" -> { aux = aux.plus(" sin(")
                       aux = aux.plus(numeros[historialvalido])
                       aux = aux.plus(")") }
            "sqrt" -> { aux = aux.plus(" sqrt(")
                        aux = aux.plus(numeros[historialvalido])
                        aux = aux.plus(")") }
            "1 / x"-> { aux = aux.plus(" 1 / ")
                        aux = aux.plus(numeros[historialvalido])}
            "10 ^ x" -> { aux = aux.plus(" 10 ^ ")
                          aux = aux.plus(numeros[historialvalido])}
        }
        numeros[historialvalido] = aux
    }

    //En caso de borrar solo un elemento del número que se este ingresando en cualquiera de los dos displays
    fun borrarElementoHistorial():String{
        //Log.d(TAG,"Entra $historial")
        historial=historial.dropLast(1)
        //Log.d(TAG,"Sale $historial")

        return historial
    }
}
