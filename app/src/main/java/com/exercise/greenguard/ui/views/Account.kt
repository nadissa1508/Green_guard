package com.exercise.greenguard.ui.views

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercise.greenguard.R

@Composable
fun CuentaApp(modifier: Modifier = Modifier){
    val fondo = painterResource(id = R.drawable.fondo_juego2)
    val usuario = painterResource(id = R.drawable.usuario)

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(painter = fondo, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Card (modifier = Modifier, RoundedCornerShape(45.dp)) {
            Column(
                modifier = Modifier
                    .height(750.dp)
                    .width(375.dp)
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
                            LinearProgressIndicator(modifier = Modifier.height(20.dp).clip(RoundedCornerShape(20.dp), ), progress = 0.7f)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = "Glosario", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
fun CuentaPreview(){
    CuentaApp()
}