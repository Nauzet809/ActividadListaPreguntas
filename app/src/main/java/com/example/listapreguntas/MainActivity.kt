package com.example.listapreguntas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.listapreguntas.data.inicializarYCargar
import com.example.listapreguntas.data.inicializarYCargarEstadisticas
import com.example.listapreguntas.navegacion.Menu
import com.example.listapreguntas.navegacion.NavDisplay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lista = inicializarYCargar(this)
        val estadistica = inicializarYCargarEstadisticas(this)
        setContent {
            val pila = rememberNavBackStack(Menu)
            NavDisplay(pila = pila, lista, estadistica)
        }
    }
}