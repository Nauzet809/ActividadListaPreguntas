package com.example.listapreguntas.ui.theme.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listapreguntas.R
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Composable
fun BotonMovimiento(aumentarIndice : () -> Unit, icono : Int, texto : String, aumentar : Boolean){
    Button(
        modifier = Modifier
            .padding(5.dp),
        onClick = {
            aumentarIndice()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            if(!aumentar){
                Icon(
                    painter = painterResource(icono),
                    contentDescription = texto
                )
            }
            Text(texto)
            if(aumentar){
                Icon(
                    painter = painterResource(icono),
                    contentDescription = texto
                )
            }
        }

    }
}

@Composable
fun Boton(contenido : Boolean, fondo : Color, onRespuesta : (Boolean) -> Unit){
    Button(
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = fondo,
            contentColor = Color.White
        ),
        onClick = {
            onRespuesta(contenido)
        }
    ) {
        Text("$contenido")
    }
}

@Composable
fun FilasOpciones(modificacion : Modifier, opcionDer : String, opcionIzq : String, fondoOpcionDer : Color, fondoOpcionIzq : Color, onSeleccion1 : () -> Unit, onSeleccion2 : () -> Unit){
    Row(
        modificacion
            .fillMaxSize()
            .padding(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        botonDeMenu(opcionDer, fondoOpcionDer, modifier = Modifier.weight(1f), onSeleccion1)
        botonDeMenu(opcionIzq, fondoOpcionIzq, modifier = Modifier.weight(1f), onSeleccion2)
    }
}

@Composable
fun botonDeMenu(opcion : String, fondo : Color, modifier : Modifier, onSeleccion : () -> Unit){
    Button (
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = fondo,
            contentColor = Color.White
        ),
        onClick = {onSeleccion}
    ) {
        Text(opcion, modifier = Modifier, textAlign = TextAlign.Center)
    }
}

@Composable
fun textoIndicado(texto : String, colorLetra : Color? = Color.White){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalArrangement = Arrangement.Center
    ) {
        if (colorLetra != null) {
            Text(texto, fontSize = 20.sp, color = colorLetra, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun mostrarImagen(imagen: Int?){
    if(imagen != 0){
        imagen?.let {
            Image(
                painter = painterResource(it),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

}


@Composable
fun LinearProgressIndicator(tamanoLista : Int, indice : Int) {
    val progreso = (indice).toFloat() / tamanoLista

    LinearProgressIndicator(
        progress = { progreso },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Dialogo(pila : NavBackStack<NavKey>, imagen : Int, aciertos : Int){
    AlertDialog(
        onDismissRequest = {
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(imagen),
                    contentDescription = stringResource(R.string.imagen_de_la_pregunta),
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(stringResource(R.string.has_sacado, aciertos))
            }
        },
            confirmButton = {
            TextButton(
                onClick = {
                    pila.removeLast()
                }
            ) {
                Text(stringResource(R.string.volver_a_jugar))
            }
        }
    )

}