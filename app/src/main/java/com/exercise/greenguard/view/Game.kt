package com.exercise.greenguard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercise.greenguard.R
import com.exercise.greenguard.ui.theme.RedLight
import com.exercise.greenguard.ui.theme.Orange
import com.exercise.greenguard.ui.theme.OrangeLight
import com.exercise.greenguard.ui.theme.Yellow
import com.exercise.greenguard.ui.theme.Gray
import com.exercise.greenguard.ui.theme.GreenGuardTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.exercise.greenguard.viewmodel.CardViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.exercise.greenguard.GreenGuardScreen


@Composable
fun GameResources(
    modifier: Modifier = Modifier,
    cuenta: () -> Unit,
) {
    val fondo = painterResource(id = R.drawable.fondo_juego2)
    val conservacion = painterResource(id = R.drawable.icon_conservacion)
    val desafios = painterResource(id = R.drawable.icon_desafios)
    val desastres = painterResource(id = R.drawable.icon_desastres)
    val positivos = painterResource(id = R.drawable.icon_ev_positivos)
    val usuario = painterResource(id = R.drawable.usuario)

    Box(modifier) {

        Image(
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "RECURSOS: 310",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .clickable { cuenta() },
                    contentScale = ContentScale.Crop,
                    painter = usuario,
                    contentDescription = null
                )
            }


            Spacer(modifier = Modifier.height(180.dp))

            //fila para arboles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                val arbol = painterResource(id = R.drawable.arbol)

                Image(
                    painter = arbol,
                    contentDescription = "Árbol",
                    modifier = Modifier.size(120.dp)
                )
                Image(
                    painter = arbol,
                    contentDescription = "Árbol",
                    modifier = Modifier.size(120.dp)
                )
                Image(
                    painter = arbol,
                    contentDescription = "árbol",
                    modifier = Modifier.size(120.dp)
                )

            }

            //fila para maiz
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                val elotes = painterResource(id = R.drawable.elotes)

                Image(
                    painter = elotes,
                    contentDescription = "Elote",
                    modifier = Modifier.size(90.dp)
                )
                Image(
                    painter = elotes,
                    contentDescription = "Elote",
                    modifier = Modifier.size(90.dp)
                )
                Image(
                    painter = elotes,
                    contentDescription = "Elote",
                    modifier = Modifier.size(90.dp)
                )


            }


            //fila para flores, arbustos y venado

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                val flores = painterResource(id = R.drawable.flores)
                val arbusto = painterResource(id = R.drawable.arbusto)
                val venado = painterResource(id = R.drawable.venado)

                Image(
                    painter = flores,
                    contentDescription = "Flores",
                    modifier = Modifier.size(100.dp)
                )
                Image(
                    painter = venado,
                    contentDescription = "Venado",
                    modifier = Modifier.size(130.dp)
                )
                Image(
                    painter = arbusto,
                    contentDescription = "Arbusto",
                    modifier = Modifier.size(100.dp)
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            //tarjetas del juego

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Tarjetas(color = RedLight, icono = desastres, onClick = { CardViewModel.seleccionarTarjetaAleatoria("desastres naturales") })
                Tarjetas(color = Orange, icono = desafios, onClick = { CardViewModel.seleccionarTarjetaAleatoria("desafios ambientales") })
                Tarjetas(color = OrangeLight, icono = positivos, onClick = { CardViewModel.seleccionarTarjetaAleatoria("eventos positivos") })
                Tarjetas(color = Yellow, icono = conservacion, onClick = { CardViewModel.seleccionarTarjetaAleatoria("acciones de conservacion") })
            }
        }
        GameScreen()
    }
}

@Composable
fun Tarjetas(
    modifier: Modifier = Modifier,
    color: Color,
    icono: Painter,
    onClick: () -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        modifier = Modifier
            .height(150.dp)
            .width(90.dp)
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = icono,
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun DescripcionDialog(
    titulo: String,
    descripcion: String,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = titulo, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = descripcion)
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cerrar")
            }
        }
    )
}


@Composable
fun TarjetaSeleccionada(
    color: Color,
    titulo: String,
    accion: String,
    onInfoClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .size(width = 200.dp, height = 300.dp)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título y Acción
            Text(
                text = titulo,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = accion,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            // Ícono de Información
            Image(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onInfoClick() }
            )
        }
    }
}

@Composable
fun GameScreen() {

    val tarjetaSeleccionada by CardViewModel.tarjetaSeleccionada.collectAsState()
    var showDescripcionDialog by remember { mutableStateOf(false) }
    
    tarjetaSeleccionada?.let { tarjeta ->
        TarjetaSeleccionada(
            color = when (tarjeta.category) {
                "desastres naturales" -> RedLight
                "desafios ambientales" -> Orange
                "acciones de conservacion" -> OrangeLight
                "eventos positivos" -> Yellow
                else -> Gray
            },
            titulo =tarjeta.title,
            accion = tarjeta.action,
            onInfoClick = { showDescripcionDialog = true }
        )

        if (showDescripcionDialog) {
            DescripcionDialog(
                titulo = tarjeta.title,
                descripcion = tarjeta.description,
                onDismissRequest = { showDescripcionDialog = false }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista previa"

)

@Composable
fun GameResourcesPreview() {
    GreenGuardTheme {
        GameResources(
            cuenta = {}
        )
    }
}