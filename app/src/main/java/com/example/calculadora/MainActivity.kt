package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

//private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    //Primero determinamos la variable que almbraremos
    private lateinit var display:TextView
    private var recibeNum = false
    private var resultado : Double = 0.0
    private val modeloCalculadora = ModeloCalculadora()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Ahora si determinemos quien y a que alambraremos
        display = findViewById(R.id.display)
    }



    //función para tomar cualquier número que se presione
    fun numPresionado(unBoton:View){
        val digito = (unBoton as Button).text
        //este if es para que se escriba el numero que entra sin el 0 inicial
        if(recibeNum){
            display.append(digito)
        }else{
            display.text = digito
            recibeNum = true
        }
    }

    //función para Operaciones Basicos
    fun operacion(unBoton: View){
        val operacionRecibida = (unBoton as Button).text.toString()
        if(recibeNum){
            modeloCalculadora.setOperando(display.text.toString().toDouble())
            recibeNum = false

        }
        resultado = modeloCalculadora.ejecutaOperacionBasica(operacionRecibida)
        val rounded = String.format("%.2f", resultado)
        display.text = rounded
    }

    //función para Operaciones Complejas con el número encontrado en el display
    fun operacionCompleja(unBoton: View){
        val operacionRecibida = (unBoton as Button).text.toString()
        modeloCalculadora.setOperando(display.text.toString().toDouble())
        resultado = modeloCalculadora.ejecutaOperacionCompleja(operacionRecibida)
        val rounded = String.format("%.2f", resultado)
        display.text = rounded
    }

    //Existe otra manera de alambrar que basiamente es crear una función que se llame cuando se presione el boton
    @Suppress("UNUSED_PARAMETER")
    //El Supress basicamente es para decir que se ignore ese warning
    //En este caso lo usamos por que no hay forma de eliminar el warning
    fun punto(unBoton: View){
        if(recibeNum){
            display.text = modeloCalculadora.validaPunto(display.text.toString())
        }else{
            display.text = modeloCalculadora.validaPunto("0")
            recibeNum = true
        }
    }
}