package com.example.listapreguntas.data

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.listapreguntas.R
import com.example.listapreguntas.model.EstadisticaPersonal
import com.example.listapreguntas.model.Pregunta
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter

var ficheroPregunta = "preguntas.txt"
var ficheroEstadisticas = "estadisticas.txt"

var listaPreguntasPrueba = listOf(
    Pregunta("La Revolución Francesa comenzó en el año 1789", R.drawable.revfrancesa, true),
    Pregunta("Cristóbal Colón fue el primer europeo en llegar a América", R.drawable.cristobalcolon, false),
    Pregunta("El Imperio Romano de Occidente cayó en el año 476 d.C.", R.drawable.caidaimpromano, true),
    Pregunta("Napoleón Bonaparte nació en Francia continental.", R.drawable.napoleon, false),
    Pregunta("La Primera Guerra Mundial terminó en 1918", R.drawable.gwfinal, true),
    Pregunta("La Gran Muralla China se construyó completamente en un solo reinado", R.drawable.granmurallachina, false),
    Pregunta("La Declaración de Independencia de Estados Unidos se firmó en 1776.", R.drawable.decindependencia, true),
    Pregunta("Cleopatra VII era de origen puramente egipcio.", R.drawable.cleopatra, false),
    Pregunta("La Segunda Guerra Mundial comenzó con la invasión de Polonia en 1939.", R.drawable.invasionpolonia, true),
    Pregunta("El Titanic se hundió en 1914.", R.drawable.titanic, false)
)

var estadistica = EstadisticaPersonal(0,0, listaPreguntasPrueba.size)

fun inicializarYCargar(contexto : Context) : List<Pregunta> {
    var ficheroPreguntas = File(contexto.filesDir, ficheroPregunta)

    if(!ficheroPreguntas.exists()){
        guardarAFichero(contexto, listaPreguntasPrueba)
    }

    return FileInputStream(ficheroPreguntas).bufferedReader().useLines { lineas ->
        lineas.map { linea ->
            val partes = linea.split(";")
            Pregunta(
                pregunta = partes[0],
                imagen = partes[1].toInt().takeIf { it != 0 },
                respuesta = partes[2].toBoolean()
            )
        }.toList()
    }
}

fun inicializarYCargarEstadisticas(contexto : Context) : EstadisticaPersonal {
    var ficheroEstadistica = File(contexto.filesDir, ficheroEstadisticas)

    if(!ficheroEstadistica.exists()){
        guardarEstadisticaAFichero(contexto, estadistica)
    }
    var partes = FileInputStream(ficheroEstadistica).bufferedReader().readLine().split(";")
    return EstadisticaPersonal(
                aciertos = partes[0].toInt(),
                errores = partes[1].toInt(),
                preguntasTotales = partes[2].toInt()
            )
    }


fun guardarAFichero(contexto : Context, listaPreguntas : List<Pregunta>){
    var ficheroPreguntas = File(contexto.filesDir, ficheroPregunta)
    val bw = BufferedWriter(FileWriter(ficheroPreguntas))
    for (pregunta in listaPreguntas){
        bw.write(pregunta.toString())
        bw.newLine()
    }
    bw.close()
}

fun guardarEstadisticaAFichero(contexto : Context, estadistica : EstadisticaPersonal){
    var ficheroPreguntas = File(contexto.filesDir, ficheroEstadisticas)
    val bw = BufferedWriter(FileWriter(ficheroPreguntas))
    bw.write(estadistica.toString())
    bw.close()
}

fun guardarPreguntaAFichero(contexto : Context, pregunta : Pregunta){
    var ficheroPreguntas = File(contexto.filesDir, ficheroPregunta)
    var fw = FileWriter(ficheroPreguntas, true)
    fw.write(pregunta.toString())
    fw.close()
}


