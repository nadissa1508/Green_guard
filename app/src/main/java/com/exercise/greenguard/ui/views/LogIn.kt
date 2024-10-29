package com.exercise.greenguard.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.exercise.greenguard.ui.theme.*


class LogIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenGuardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogInScreen(
                        modifier = Modifier.padding(innerPadding),
                        siguiente = {},
                        juego = {}
                    )
                }
            }
        }
    }
}

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
    val fondo = painterResource(id = R.drawable.fondo_juego)
    val icon = painterResource(id = R.drawable.logo_green_guard)

    var username by remember { mutableStateOf("") }
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

                ContentColorComponent(contentColor = Color.LightGray) {
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Nombre de usuario") },
                        shape = RoundedCornerShape(26.dp),
                        colors = TextFieldDefaults.colors(
                            focusedLabelColor = Gray,
                            unfocusedLabelColor = Gray,
                            unfocusedIndicatorColor = White,
                            focusedIndicatorColor = White,
                            focusedContainerColor = LightGray,
                            unfocusedContainerColor = LightGray,
                            disabledContainerColor = LightGray

                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)


                    )
                }

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(26.dp),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Gray,
                        unfocusedLabelColor = Gray,
                        unfocusedIndicatorColor = White,
                        focusedIndicatorColor = White,
                        focusedContainerColor = LightGray,
                        unfocusedContainerColor = LightGray,
                        disabledContainerColor = LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)

                )

                ElevatedButton(
                    onClick = juego,
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
                ClickableText(
                    text = AnnotatedString("¿No tienes una cuenta? Registrate"),
                    onClick = {siguiente()},
                    modifier = Modifier.padding(vertical = 8.dp)
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
        LogInScreen(
            siguiente = {},
            juego = {}
            )
    }
}