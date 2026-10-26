package com.example.listapreguntas.model

import com.example.listapreguntas.data.listaPreguntasPrueba

class EstadisticaPersonal(var aciertos : Int, var errores : Int, var preguntasTotales : Int? = listaPreguntasPrueba.size) {
    @Override
    override fun toString() : String{
        return "$aciertos;$errores;$preguntasTotales"
    }

    fun clicks() : Int{
        return aciertos + errores
    }

    fun procentageAciertos() : Float {
        return aciertos.toFloat()/(aciertos+errores).toFloat()
    }
}
