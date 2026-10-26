package com.example.listapreguntas.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.listapreguntas.model.EstadisticaPersonal
import com.example.listapreguntas.model.Pregunta
import com.example.listapreguntas.ui.theme.pantallas.CrearEscribir
import com.example.listapreguntas.ui.theme.pantallas.CrearEstadisticas
import com.example.listapreguntas.ui.theme.pantallas.CrearMenu
import com.example.listapreguntas.ui.theme.pantallas.CrearPantalla
import com.example.listapreguntas.ui.theme.pantallas.CrearPreguntasExamen
import com.example.listapreguntas.ui.theme.pantallas.CrearPreguntasSpeedRun
import com.example.listapreguntas.ui.theme.pantallas.CrearSubMenu

@Composable
fun NavDisplay(
    pila : NavBackStack<NavKey>,
    listaPregunta : List<Pregunta>,
    estadistica : EstadisticaPersonal
) {
    var estadisticaAux by remember { mutableStateOf(estadistica) }
    val listaAux = remember { listaPregunta.toMutableStateList() }
    NavDisplay(
        backStack = pila,
        onBack = {pila.removeLastOrNull()},


        entryProvider = entryProvider {
            entry<Menu> {
                CrearMenu(pila)
            }

            entry<SubMenu> {
                CrearSubMenu(pila)
            }

            entry<PreguntasBasicas> {
                estadisticaAux = CrearPantalla(listaAux, estadisticaAux)
            }

            entry<PreguntaExamen> {
                CrearPreguntasExamen(listaAux, pila)
            }

            entry<PreguntaSpeedRun> {
                estadisticaAux = CrearPreguntasSpeedRun(listaAux, estadisticaAux)
            }

            entry<Estadisticas> {
                CrearEstadisticas(estadisticaAux)
            }

            entry<Escribir> {
                CrearEscribir(estadisticaAux, listaAux, pila)
            }

        }

    )
}