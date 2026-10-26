package com.example.listapreguntas.navegacion

import androidx.compose.runtime.Composable
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
    var estadisticaAux = estadistica
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
                estadisticaAux = CrearPantalla(listaPregunta, estadistica)
            }

            entry<PreguntaExamen> {
                CrearPreguntasExamen(listaPregunta, pila)
            }

            entry<PreguntaSpeedRun> {
                estadisticaAux = CrearPreguntasSpeedRun(listaPregunta, estadistica)
            }

            entry<Estadisticas> {
                CrearEstadisticas(
                    estadistica
                )
            }

            entry<Escribir> {
                estadisticaAux = CrearEscribir(estadistica)
            }

        }

    )
}