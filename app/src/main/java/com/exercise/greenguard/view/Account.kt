package com.exercise.greenguard.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
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
import com.exercise.greenguard.R

@Composable
fun CuentaApp(modifier: Modifier = Modifier){
    val fondo = painterResource(id = R.drawable.fondo_juego2)
    val usuario = painterResource(id = R.drawable.usuario)

    val conservacion = painterResource(id = R.drawable.icon_conservacion)
    val desafíos = painterResource(id = R.drawable.icon_desafios)
    val desastres = painterResource(id = R.drawable.icon_desastres)
    val positivos = painterResource(id = R.drawable.icon_ev_positivos)

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
                                .clip(RoundedCornerShape(20.dp),), progress = 0.7f)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "Glosario",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.fillMaxWidth(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        Logros(icono = conservacion, color = Color(0xFFffde59))
                        Logros(icono = desafíos, color = Color(0xFFff914d))
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Logros(icono = positivos, color = Color(0xFFffbd59))
                        Logros(icono = desastres, color = Color(0xFFff3131))
                    }
                }
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