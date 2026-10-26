package com.example.listapreguntas.model

import android.media.Image

data class Pregunta(val pregunta : String, val imagen : Int? = 0, val respuesta : Boolean){

    @Override
    override fun toString() : String{
        return "$pregunta;$imagen;$respuesta"
    }
}
