package com.example.listapreguntas.ui.theme.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listapreguntas.ui.theme.componentes.textoIndicado
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.listapreguntas.ui.theme.componentes.Boton
import com.example.listapreguntas.ui.theme.componentes.BotonMovimiento
import com.example.listapreguntas.R
import com.example.listapreguntas.data.guardarAFichero
import com.example.listapreguntas.data.guardarEstadisticaAFichero
import com.example.listapreguntas.data.guardarPreguntaAFichero
import com.example.listapreguntas.model.EstadisticaPersonal
import com.example.listapreguntas.model.Pregunta

@Composable
fun CrearEscribir(estadistica : EstadisticaPersonal, lista : MutableList<Pregunta>, pila : NavBackStack<NavKey>) : List<Pregunta>{
    var texto by remember { mutableStateOf("") }
    var respuesta by remember { mutableStateOf(true) }
    val fondoBtn = Color.Blue
    var fondoBtn1 by remember { mutableStateOf(Color.Green) }
    var fondoBtn2 by remember { mutableStateOf(fondoBtn) }
    var estadisticaAux = estadistica

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = texto,
            onValueChange = { nuevoTexto : String -> texto = nuevoTexto },
            label = { Text(stringResource(R.string.pregunta), color = Color.Black) },
            placeholder = { Text(stringResource(R.string.cleopatra_vii_era_de_origen_puramente_egipcio)) },
            singleLine = true
        )



        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Boton(true, fondoBtn1,
                {
                    fondoBtn1 = Color.Green
                    respuesta = true
                    fondoBtn2 = fondoBtn
                }
            )
            Boton(false, fondoBtn2,
            {
                fondoBtn2 = Color.Red
                respuesta = false
                fondoBtn1 = fondoBtn
            }
            )
        }

        val contexto = LocalContext.current
        BotonMovimiento({
            if(texto.isNotEmpty()){
                estadisticaAux.preguntasTotales = estadisticaAux.preguntasTotales?.plus(1)
                Toast.makeText(contexto, "Pregunta guardada", Toast.LENGTH_SHORT).show()

                lista.add(Pregunta(texto, 0, respuesta))
                guardarAFichero(contexto, lista)
                guardarEstadisticaAFichero(contexto, estadisticaAux)

                pila.removeAt(pila.lastIndex)
            } else {
                Toast.makeText(contexto, "Pregunta sin texto", Toast.LENGTH_LONG).show()
            }
        },
            R.drawable.guardar,
            stringResource(R.string.guardar),
            true, true)
    }

    return lista.toList()
}

@Preview
@Composable
fun PreviewCrearEscribir(){
    //CrearEscribir(EstadisticaPersonal(5,5))
}