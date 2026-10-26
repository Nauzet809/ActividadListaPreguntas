package com.example.listapreguntas.navegacion

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Menu : NavKey

@Serializable
data object PreguntasBasicas : NavKey

@Serializable
data object Estadisticas : NavKey

@Serializable
data object Escribir : NavKey

@Serializable
data object SubMenu : NavKey

@Serializable
data object PreguntaSpeedRun : NavKey

@Serializable
data object PreguntaExamen : NavKey