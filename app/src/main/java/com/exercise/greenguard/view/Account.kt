package com.exercise.greenguard.view

import CardViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exercise.greenguard.R
import com.exercise.greenguard.data.model.Tarjeta
import com.exercise.greenguard.ui.theme.Gray
import com.exercise.greenguard.ui.theme.Orange
import com.exercise.greenguard.ui.theme.OrangeLight
import com.exercise.greenguard.ui.theme.RedLight
import com.exercise.greenguard.ui.theme.Yellow

@Composable
fun CuentaApp(
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel = viewModel()
) {
    val fondo = painterResource(id = R.drawable.fondo_juego2)
    val usuario = painterResource(id = R.drawable.usuario)
    val tarjetas by cardViewModel.userTarjetas.collectAsState()
    val totalTarjetas = 20
    val progreso = tarjetas.size / totalTarjetas.toFloat()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(painter = fondo, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Card (modifier = Modifier, RoundedCornerShape(45.dp)) {
            Column(
                modifier = Modifier
                    .height(750.dp)
                    .width(355.dp)
                    .background(Color(0xfffafbf5))
            ) {
                Row (modifier = Modifier.padding(20.dp),) {
                    Image(
                        modifier = Modifier
                            .size(85.dp)
                            .clip(RoundedCornerShape(100.dp)),
                        contentScale = ContentScale.Crop,
                        painter = usuario,
                        contentDescription = null
                    )
                    Column(modifier = Modifier.padding(horizontal = 20.dp)){
                        Text(modifier = Modifier.padding(bottom = 10.dp), text = "Mi Cuenta", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        Column {
                            Text(text = "PROGRESO")
                            LinearProgressIndicator(modifier = Modifier
                                .height(20.dp)
                                .clip(RoundedCornerShape(20.dp),), progress = progreso.coerceIn(0f, 1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Glosario",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Dos columnas
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(tarjetas) { tarjeta ->
                        TarjetaCard(
                            tarjeta = tarjeta,
                            color = when (tarjeta.categoria) {
                                "desastres naturales" -> RedLight
                                "desafios ambientales" -> Orange
                                "acciones de conservacion" -> OrangeLight
                                "eventos positivos" -> Yellow
                                else -> Gray
                            },
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaCard(modifier: Modifier = Modifier, tarjeta: Tarjeta, color: Color) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(130.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()) // Scroll interno
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var categoriaIcon = when (tarjeta.categoria) {
                    "desastres naturales" -> R.drawable.icon_desastres
                    "desafios ambientales" -> R.drawable.icon_desafios
                    "acciones de conservacion" -> R.drawable.icon_conservacion
                    "eventos positivos" -> R.drawable.icon_ev_positivos
                    else -> R.drawable.icon_desastres
                }
                Text(text = tarjeta.titulo, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = tarjeta.descripcion, fontSize = 12.sp, color = Color.White)
                Spacer(modifier = Modifier.height(6.dp))
                Image(
                    painter = painterResource(id = categoriaIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                )

            }
        }

    }
}


@Composable
fun Logros(modifier: Modifier = Modifier, icono: Painter, color: Color){
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(130.dp),
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),

        ) {
        Box (modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(modifier = Modifier.size(100.dp), painter = icono, contentDescription = null, contentScale = ContentScale.Crop)
        }
    }
}

@Preview
@Composable
fun CuentaPreview(){
    CuentaApp()
}