package com.example.listapreguntas.ui.theme.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.listapreguntas.R
import com.example.listapreguntas.navegacion.Escribir
import com.example.listapreguntas.navegacion.Estadisticas
import com.example.listapreguntas.navegacion.PreguntasBasicas
import com.example.listapreguntas.navegacion.SubMenu

@Composable
fun CrearMenu(pila : NavBackStack<NavKey>){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Primera Fila (Botón 1 y 2)
        Row(
            modifier = Modifier.weight(1f), // Ocupa la mitad vertical del espacio
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { pila.add(SubMenu)}, modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                Text(stringResource(R.string.preguntas), fontSize = 20.sp)
            }
            Button(onClick = { pila.add(Estadisticas)}, modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                Text(stringResource(R.string.estadisticas), fontSize = 20.sp)
            }
        }

        // Segunda Fila (Botón 3 y 4)
        Row(
            modifier = Modifier.weight(1f), // Ocupa la otra mitad vertical
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { pila.add(Escribir) }, modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                Text(stringResource(R.string.escribir), fontSize = 20.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCrearMenu(){
    //CrearMenu()
}