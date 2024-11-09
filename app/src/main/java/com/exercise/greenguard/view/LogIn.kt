package com.exercise.greenguard.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exercise.greenguard.R
import com.exercise.greenguard.ui.theme.GreenGuardTheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.exercise.greenguard.ui.theme.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ContentColorComponent(
    contentColor: Color = LocalContentColor.current,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides contentColor,
        content = content)
}

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    siguiente: () -> Unit,
    juego: () -> Unit,
) {
    val auth = FirebaseAuth.getInstance()
    var error by remember { mutableStateOf<String?>(null) }
    val fondo = painterResource(id = R.drawable.fondo_juego)
    val icon = painterResource(id = R.drawable.logo_green_guard)
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier) {

        Image(
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        Box(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .width(310.dp)
                .height(500.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White.copy(alpha = 0.94f))
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                        .width(200.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }

                Datos(texto = "Correo electrónico", email, onValueChange = { email = it }, false)
                Datos(texto = "Contraseña", password,onValueChange = { password = it }, true)

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)

                )

                ElevatedButton(
                    onClick = {
                        if (email.isEmpty() || password.isEmpty()) {
                            error = "Por favor completa todos los campos"
                        } else {
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        juego() //abrimos la pantalla del juego
                                        Toast.makeText(context, "Sesión iniciada correctamente.", Toast.LENGTH_SHORT).show()
                                    } else {
                                        error = task.exception?.message
                                    }
                                }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenBlue,
                        contentColor = White,
                        disabledContainerColor = GreenBlue,
                        disabledContentColor = White
                    ),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(200.dp)
                        .height(50.dp)
                ) {
                    Text(
                        "INICIAR SESIÓN",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                if(error!=null){
                    Toast.makeText(context, "Error, no es posible iniciar sesión. Intente más tarde", Toast.LENGTH_SHORT).show()
                    error = null;
                }
                ClickableText(
                    text = AnnotatedString("¿No tienes una cuenta? Registrate"),
                    onClick = {siguiente()},
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inputs(
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


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista previa"

)

@Composable
fun LogInPreview() {
    GreenGuardTheme {
        LogInScreen(
            siguiente = {},
            juego = {}
            )
    }
}