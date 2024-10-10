package com.exercise.greenguard.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exercise.greenguard.R

@Composable
fun RegistroApp(modifier: Modifier = Modifier){
    val fondo = painterResource(id = R.drawable.fondo_juego)
    val logo = painterResource(id = R.drawable.logo_green_guard)

    var nombre by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = fondo, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())

        Card (modifier = Modifier, RoundedCornerShape(45.dp)) {
            Column(
                modifier = Modifier
                    .height(650.dp)
                    .width(375.dp)
                    .background(Color(0xfffafbf5)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = logo, contentDescription = null, modifier = Modifier.size(250.dp))
                Datos(texto = "Nombre de Usuario", onValueChange = { nombre = it })
                Datos(texto = "Contraseña", onValueChange = { contraseña = it })
                Datos(texto = "Correo electrónico", onValueChange = { correo = it })
                Button(
                    modifier = Modifier.padding(25.dp)
                        .height(55.dp)
                        .width(200.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff33c7b7),
                    )
                ) {
                    Text(text = "Registrarse")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Datos(texto: String, onValueChange: (String) -> Unit,){
    TextField(
        modifier = Modifier.padding(10.dp)
            .height(45.dp),
        value = texto,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(26.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xffedf5f3)
        )
    )
}

@Composable
@Preview
fun RegistroPreview(){
    RegistroApp()
}