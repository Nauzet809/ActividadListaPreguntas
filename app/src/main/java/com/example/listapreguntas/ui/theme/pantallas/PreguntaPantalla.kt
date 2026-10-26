package com.example.listapreguntas.ui.theme.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.listapreguntas.R
import com.example.listapreguntas.data.guardarEstadisticaAFichero
import com.example.listapreguntas.data.listaPreguntasPrueba
import com.example.listapreguntas.model.EstadisticaPersonal
import com.example.listapreguntas.model.Pregunta
import com.example.listapreguntas.ui.theme.componentes.Boton
import com.example.listapreguntas.ui.theme.componentes.BotonMovimiento
import com.example.listapreguntas.ui.theme.componentes.Dialogo
import com.example.listapreguntas.ui.theme.componentes.LinearProgressIndicator
import com.example.listapreguntas.ui.theme.componentes.mostrarImagen
import com.example.listapreguntas.ui.theme.componentes.textoIndicado


@Composable
fun CrearPantalla(listaPregunta : List<Pregunta>, estadistica : EstadisticaPersonal) : EstadisticaPersonal{

    fun cambiarIndice(indice : Int, aumento : Boolean) : Int{
        var i = indice
        if((indice < listaPregunta.size -1) and aumento){
            return i + 1
        } else if(aumento){
            return 0
        } else if ((0 < indice) and (!aumento)){
            return i - 1
        } else {
            return listaPregunta.size -1
        }

    }


    var indice by remember { mutableIntStateOf(0) }

    val fondoBtn = Color.Blue
    var fondoBtn1 by remember { mutableStateOf(fondoBtn) }
    var fondoBtn2 by remember { mutableStateOf(fondoBtn) }


    val estadisticaAux = estadistica
    val preguntaActual =  listaPregunta[indice]
    val respuesta = preguntaActual.respuesta
    val imagen = preguntaActual.imagen
    val pregunta = preguntaActual.pregunta
    var btnPulsado by remember { mutableStateOf(false) }

    val textoAcierto = stringResource(R.string.has_acertado)
    val textoError = stringResource(R.string.has_errado)
    var resultado by remember { mutableStateOf("") }
    var colorTexto by remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        textoIndicado(pregunta)

        mostrarImagen(imagen)

        textoIndicado(resultado, colorTexto)

        val contexto = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Boton(true, fondoBtn1,
                {
                    if(!btnPulsado){
                        if(respuesta == it){
                            fondoBtn1 = Color.Green
                            fondoBtn2 = Color.Red
                            estadisticaAux.aciertos++
                            resultado = textoAcierto
                        } else {
                            fondoBtn1 = Color.Red
                            fondoBtn2 = Color.Green
                            estadisticaAux.errores++
                            resultado = textoError
                        }
                        btnPulsado = true
                        colorTexto = fondoBtn1
                        guardarEstadisticaAFichero(contexto, estadisticaAux)
                    }

                }
            )


            Boton(false, fondoBtn2,
                {
                    if(!btnPulsado){
                        if(respuesta == it){
                            fondoBtn1 = Color.Red
                            fondoBtn2 = Color.Green
                            estadisticaAux.aciertos++
                            resultado = textoAcierto

                        } else {
                            fondoBtn1 = Color.Green
                            fondoBtn2 = Color.Red
                            estadisticaAux.errores++
                            resultado = textoError
                        }
                        btnPulsado = true
                        colorTexto = fondoBtn2
                        guardarEstadisticaAFichero(contexto, estadisticaAux)
                    }

                }
            )
        }

        Row(

        ) {
            BotonMovimiento({
                if(fondoBtn1 != fondoBtn)indice = cambiarIndice(indice, false)
                fondoBtn1 = fondoBtn
                fondoBtn2 = fondoBtn
                resultado = ""
                btnPulsado = false
            }, R.drawable.atras, stringResource(R.string.anterior),
                false)

            BotonMovimiento({
                indice = listaPregunta.indices.random()
                fondoBtn1 = fondoBtn
                fondoBtn2 = fondoBtn
                resultado = ""
                btnPulsado = false
            }, R.drawable.random, stringResource(R.string.aleatorio),
                true)

            BotonMovimiento({
                if(fondoBtn1 != fondoBtn)indice = cambiarIndice(indice, true)
                fondoBtn1 = fondoBtn
                fondoBtn2 = fondoBtn
                btnPulsado = false
                resultado = ""
            }, R.drawable.derecha, stringResource(R.string.siguiente),
                true)
        }


    }
    return estadisticaAux
}

@Preview
@Composable
fun PreviewCrearPantalla(){
    listaPreguntasPrueba
    CrearPantalla(listaPreguntasPrueba, EstadisticaPersonal(5,5))
}


@Composable
fun CrearPreguntasSpeedRun(listaPregunta : List<Pregunta>, estadistica : EstadisticaPersonal) : EstadisticaPersonal{

    fun cambiarIndice(indice : Int, aumento : Boolean) : Int{
        var i = indice
        if((indice < listaPregunta.size -1) and aumento){
            return i + 1
        } else if(aumento){
            return 0
        } else if ((0 < indice) and (!aumento)){
            return i - 1
        } else {
            return listaPregunta.size -1
        }

    }


    var indice by remember { mutableIntStateOf(0) }

    val fondoBtn = Color.Blue
    var fondoBtn1 by remember { mutableStateOf(fondoBtn) }
    var fondoBtn2 by remember { mutableStateOf(fondoBtn) }


    val estadisticaAux = estadistica
    val preguntaActual =  listaPregunta[indice]
    val respuesta = preguntaActual.respuesta
    val imagen = preguntaActual.imagen
    val pregunta = preguntaActual.pregunta
    var btnPulsado by remember { mutableStateOf(false) }

    val textoAcierto = stringResource(R.string.has_acertado)
    val textoError = stringResource(R.string.has_errado)
    var resultado by remember { mutableStateOf("") }
    var colorTexto by remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LinearProgressIndicator(listaPregunta.size-1, indice)
        textoIndicado(pregunta)

        //mostrarImagen(imagen)

        textoIndicado(resultado, colorTexto)

        val contexto = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Boton(true, fondoBtn1,
                {
                    if(!btnPulsado){
                        if(respuesta == it){
                            fondoBtn1 = Color.Green
                            fondoBtn2 = Color.Red
                            estadisticaAux.aciertos++
                            resultado = textoAcierto
                        } else {
                            fondoBtn1 = Color.Red
                            fondoBtn2 = Color.Green
                            estadisticaAux.errores++
                            resultado = textoError
                        }
                        btnPulsado = true
                        colorTexto = fondoBtn1
                        guardarEstadisticaAFichero(contexto, estadisticaAux)
                    }

                }
            )


            Boton(false, fondoBtn2,
                {
                    if(!btnPulsado){
                        if(respuesta == it){
                            fondoBtn1 = Color.Red
                            fondoBtn2 = Color.Green
                            estadisticaAux.aciertos++
                            resultado = textoAcierto

                        } else {
                            fondoBtn1 = Color.Green
                            fondoBtn2 = Color.Red
                            estadisticaAux.errores++
                            resultado = textoError
                        }
                        btnPulsado = true
                        colorTexto = fondoBtn2
                        guardarEstadisticaAFichero(contexto, estadisticaAux)
                    }

                }
            )
        }

        Row(

        ) {
            BotonMovimiento({
                if(fondoBtn1 != fondoBtn)indice = cambiarIndice(indice, false)
                fondoBtn1 = fondoBtn
                fondoBtn2 = fondoBtn
                resultado = ""
                btnPulsado = false
            }, R.drawable.atras, stringResource(R.string.anterior),
                false)

            BotonMovimiento({
                if(fondoBtn1 != fondoBtn)indice = cambiarIndice(indice, true)
                fondoBtn1 = fondoBtn
                fondoBtn2 = fondoBtn
                btnPulsado = false
                resultado = ""
            }, R.drawable.derecha, stringResource(R.string.siguiente),
                true)
        }


    }
    return estadisticaAux
}

@Preview
@Composable
fun PreviewCrearPreguntasSpeedRun(){
    listaPreguntasPrueba
    CrearPreguntasSpeedRun(listaPreguntasPrueba, EstadisticaPersonal(5,5))
}


@Composable
fun CrearPreguntasExamen(listaPregunta : List<Pregunta>, pila : NavBackStack<NavKey>){

    var indice by remember { mutableIntStateOf(0) }

    val fondoBtn = Color.Blue
    var fondoBtn1 by remember { mutableStateOf(fondoBtn) }
    var fondoBtn2 by remember { mutableStateOf(fondoBtn) }


    val preguntaActual =  listaPregunta[indice]
    val respuesta = preguntaActual.respuesta
    var imagen = preguntaActual.imagen
    val pregunta = preguntaActual.pregunta
    var btnPulsado by remember { mutableStateOf(false) }

    val textoAcierto = stringResource(R.string.has_acertado)
    val textoError = stringResource(R.string.has_errado)
    var resultado by remember { mutableStateOf("") }
    var colorTexto by remember { mutableStateOf(Color.White) }
    var acertado by remember { mutableStateOf(0) }

    fun aciertos() : Int{
        return ((acertado.toFloat()/listaPregunta.size)*100).toInt()
    }


    fun cambiarIndice(indice : Int) : Int{
        var i = indice
        if((indice < listaPregunta.size -1)) {
            return i + 1
        }
        return 0
    }

    if(indice == listaPregunta.size -1){
        if(aciertos() == 100){
            Dialogo(pila, R.drawable.celebrar100, aciertos())
        } else if(aciertos() > 50){
            Dialogo(pila, R.drawable.bien, aciertos())
        } else if(aciertos() > 20){
            Dialogo(pila , R.drawable.casi, aciertos())
        } else {
            Dialogo(pila , R.drawable.muymalanota, aciertos())
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        textoIndicado(pregunta)

        mostrarImagen(imagen)

        textoIndicado(resultado, colorTexto)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Boton(true, fondoBtn1,
                {
                    if(!btnPulsado){
                        if(respuesta == it){
                            fondoBtn1 = Color.Green
                            fondoBtn2 = Color.Red
                            resultado = textoAcierto
                            acertado++
                        } else {
                            fondoBtn1 = Color.Red
                            fondoBtn2 = Color.Green
                            resultado = textoError
                        }
                        btnPulsado = true
                        colorTexto = fondoBtn1
                    }

                }
            )


            Boton(false, fondoBtn2,
                {
                    if(!btnPulsado){
                        if(respuesta == it){
                            fondoBtn1 = Color.Red
                            fondoBtn2 = Color.Green
                            resultado = textoAcierto
                            acertado++

                        } else {
                            fondoBtn1 = Color.Green
                            fondoBtn2 = Color.Red
                            resultado = textoError
                        }
                        btnPulsado = true
                        colorTexto = fondoBtn2
                    }

                }
            )
        }

        Row(

        ) {
            BotonMovimiento({
                if(fondoBtn1 != fondoBtn)indice = cambiarIndice(indice)
                fondoBtn1 = fondoBtn
                fondoBtn2 = fondoBtn
                btnPulsado = false
                resultado = ""
            }, R.drawable.derecha, stringResource(R.string.siguiente),
                true)
        }


    }
}

@Preview
@Composable
fun PreviewCrearPreguntasExamen(){
    listaPreguntasPrueba
    //CrearPreguntasExamen(listaPreguntasPrueba, pila)
}