package com.exercise.greenguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exercise.greenguard.ui.theme.GreenGuardTheme
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MenuElements(
    modifier: Modifier = Modifier,
    siguiente: () -> Unit,
    juego: () -> Unit
){
    val fondo = painterResource(id = R.drawable.fondo_juego)
    val icon = painterResource(id = R.drawable.logo_green_guard)
    Box(modifier){
        Image(
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Box(
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .width(200.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    //cambiar al color por medio de theme
                    .background(Color.White )
                    .align(Alignment.CenterHorizontally) ,

            ){
                Image(
                    painter = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            ElevatedButton(
                onClick = juego,
                modifier = Modifier.padding(vertical = 4.dp)
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    "INICIAR JUEGO",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            ElevatedButton(
                onClick = siguiente,
                modifier = Modifier.padding(vertical = 8.dp)
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    "INICIAR SESIÓN",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista previa"

)

@Composable
fun MenuElementsPreview() {
    GreenGuardTheme {
        MenuElements(
            siguiente = {},
            juego = {}
        )
    }
}