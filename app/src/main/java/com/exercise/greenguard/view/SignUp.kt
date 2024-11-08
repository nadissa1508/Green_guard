package com.exercise.greenguard.view

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
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.launch

@Composable
fun RegistroApp(modifier: Modifier = Modifier, siguiente: () -> Unit){
    val auth = FirebaseAuth.getInstance()
    val scope = rememberCoroutineScope()
    val fondo = painterResource(id = R.drawable.fondo_juego)
    val logo = painterResource(id = R.drawable.logo_green_guard)
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = fondo, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())

        Card (modifier = Modifier, RoundedCornerShape(45.dp)) {
            Column(
                modifier = Modifier
                    .height(600.dp)
                    .width(350.dp)
                    .background(Color(0xfffafbf5)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = logo, contentDescription = null, modifier = Modifier.size(210.dp))
                Datos(texto = "Nombre de Usuario", name,onValueChange = { name = it }, false)
                Datos(texto = "Correo electrónico", email, onValueChange = { email = it }, false)
                Datos(texto = "Contraseña", password,onValueChange = { password = it }, true)

                Button(
                    modifier = Modifier.padding(25.dp)
                        .height(55.dp)
                        .width(200.dp),
                    onClick = {
                                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                                    error = "Por favor completa todos los campos"
                                } else {
                                    scope.launch {
                                        auth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    siguiente()
                                                } else {
                                                    error = task.exception?.message
                                                }
                                            }
                                    }
                                }
                              },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff33c7b7),
                    )
                ) {
                    Text(text = "Registrarse")
                }
                if (error != null) {
                    Toast.makeText(context, "Error, no es posible registrarse. Intente más tarde", Toast.LENGTH_SHORT).show()
                    error = null;
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Datos(
    texto: String,
    value: String,
    onValueChange: (String) -> Unit,
    esPassword: Boolean
) {
    TextField(
        modifier = Modifier
            .height(75.dp)
            .padding(vertical = 10.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(26.dp),
        keyboardOptions = if (esPassword) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else {
            KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        },
        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xffedf5f3)
        ),
        label = { Text(text = texto) }
    )
}


@Composable
@Preview
fun RegistroPreview(){
    RegistroApp(siguiente = {})
}