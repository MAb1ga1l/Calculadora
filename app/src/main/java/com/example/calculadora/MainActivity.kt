package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

//private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    //Primero determinamos la variable que almbraremos
    private lateinit var display:TextView
    private lateinit var displayOperaciones : TextView
    private var resultado : Double = 0.0
    private val calculadoreViewModel :CalculadoreViewModel by lazy {
       //Sección para mantener el estado a pesar de que se rote el telefono
       //Primero iniciamos un provider
       //Ahora creamos un View Model de calculadora
       //De tal forma que el View Model sobrevive a la rotación y se destruye hasta que se cierra la app
        ViewModelProvider(this).get(CalculadoreViewModel::class.java)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Ahora si determinemos quien y a que alambraremos
        display = findViewById(R.id.display)
        displayOperaciones = findViewById(R.id.displayOperaciones)
    }

    override fun onDestroy() {
        super.onDestroy()
        //Para guardar estado correctamente mandamos y guardamos lo que se encuentre e el display al ViewModel
        calculadoreViewModel.resultado = display.text.toString().toDouble()
        calculadoreViewModel.guardarEstadoHistorial()
    }

    override fun onStart() {
        super.onStart()
        //Se retoma lo que este guardado
        display.text = calculadoreViewModel.resultado.toString()
        displayOperaciones.text = calculadoreViewModel.retomarEstadoHistorial()
    }

    //función para tomar cualquier número que se presione
    fun numPresionado(unBoton:View){
        val digito = (unBoton as Button).text
        //este if es para que se escriba el numero que entra sin el 0 inicial
        if(calculadoreViewModel.recibeNum){
            display.append(digito)
        }else{
            display.text = digito
            calculadoreViewModel.recibeNum = true
        }
        displayOperaciones.text = calculadoreViewModel.historialOperaciones(digito.toString(),"numero")
    }

    //función para Operaciones Basicos
    fun operacion(unBoton: View){
        val operacionRecibida = (unBoton as Button).text.toString()
        if(calculadoreViewModel.recibeNum){
            calculadoreViewModel.setOperando(display.text.toString().toDouble())
            calculadoreViewModel.recibeNum = false
        }
        resultado = calculadoreViewModel.ejecutaOperacionBasica(operacionRecibida)
        val rounded = String.format("%.2f", resultado)
        display.text = rounded
        displayOperaciones.text = calculadoreViewModel.historialOperaciones(rounded,"numero")
        displayOperaciones.text = calculadoreViewModel.historialOperaciones(operacionRecibida,"opI")
    }

    //función para Operaciones Complejas con el número encontrado en el display
    fun operacionCompleja(unBoton: View){
        val operacionRecibida = (unBoton as Button).text.toString()
        displayOperaciones.text = calculadoreViewModel.historialOperaciones(operacionRecibida,"opC")
        calculadoreViewModel.setOperando(display.text.toString().toDouble())
        resultado = calculadoreViewModel.ejecutaOperacionCompleja(operacionRecibida)
        val rounded = String.format("%.2f", resultado)
        display.text = rounded
        calculadoreViewModel.recibeNum = false
    }

    //Existe otra manera de alambrar que basiamente es crear una función que se llame cuando se presione el boton
    @Suppress("UNUSED_PARAMETER")
    //El Supress basicamente es para decir que se ignore ese warning
    //En este caso lo usamos por que no hay forma de eliminar el warning
    fun punto(unBoton: View){
        if(calculadoreViewModel.recibeNum){
            display.text = calculadoreViewModel.validaPunto(display.text.toString())
        }else{
            display.text = calculadoreViewModel.validaPunto("0")
            calculadoreViewModel.recibeNum = true
        }
    }

    //función para botones con efectos en la memoria
    fun operacionesMemoria(unBoton: View){
        val operacionRecibida = (unBoton as Button).text.toString()
        resultado = calculadoreViewModel.ejecutaOperacionMemoria(operacionRecibida,display.text.toString().toDouble())
        display.text = resultado.toString()
        displayOperaciones.text = calculadoreViewModel.historialOperaciones(resultado.toString(),"numero")
        calculadoreViewModel.recibeNum = false
    }

    @Suppress("UNUSED_PARAMETER")
    fun borrarElemento(unBoton: View){
        if(calculadoreViewModel.recibeNum){
            display.text = display.text.toString().dropLast(1)
            displayOperaciones.text = calculadoreViewModel.borrarElementoHistorial()
        }
    }

    //función para AC que es borrar todo lo existente
    @Suppress("UNUSED_PARAMETER")
    fun limpiarCalculadora(unBoton: View){
        display.text = "0"
        displayOperaciones.text = "0"
        calculadoreViewModel.recibeNum = false
        calculadoreViewModel.limpiaTodo()
    }

}