package com.example.listapreguntas.ui.theme.pantallas

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listapreguntas.R
import com.example.listapreguntas.model.EstadisticaPersonal
import com.example.listapreguntas.ui.theme.componentes.textoIndicado

@Composable
fun CrearEstadisticas(estadistica : EstadisticaPersonal){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        textoIndicado("Aciertos: ${estadistica.aciertos}")
        textoIndicado("Errores: ${estadistica.errores}")
        textoIndicado("Clicks: ${estadistica.clicks()}")
        textoIndicado("% Aciertos: ${estadistica.procentageAciertos()} %")
       textoIndicado("Preguntas totales:  ${estadistica.preguntasTotales}")
    }
}

@Preview
@Composable
fun PreviewCrearEstadisticas(){
    CrearEstadisticas(EstadisticaPersonal(5,5))
}