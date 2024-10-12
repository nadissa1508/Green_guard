package com.exercise.greenguard.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercise.greenguard.R
import com.exercise.greenguard.ui.theme.RedLight
import com.exercise.greenguard.ui.theme.Orange
import com.exercise.greenguard.ui.theme.OrangeLight
import com.exercise.greenguard.ui.theme.Yellow
import com.exercise.greenguard.ui.theme.GreenGuardTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.painter.Painter

class Game : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenGuardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameResources(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}


@Composable
fun GameResources(
    modifier: Modifier = Modifier,

) {
    val fondo = painterResource(id = R.drawable.fondo_juego2)
    val conservacion = painterResource(id = R.drawable.icon_conservacion)
    val desafíos = painterResource(id = R.drawable.icon_desafios)
    val desastres = painterResource(id = R.drawable.icon_desastres)
    val positivos = painterResource(id = R.drawable.icon_ev_positivos)

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
            Text(
                text = "RECURSOS: 310",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(vertical = 8.dp)
            )

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
                Tarjetas(color = RedLight, icono = desastres)
                Tarjetas(color = Orange, icono = desafíos)
                Tarjetas(color = OrangeLight, icono = positivos)
                Tarjetas(color = Yellow, icono = conservacion)
            }


        }


    }
}

@Composable
fun Tarjetas(modifier: Modifier = Modifier, color: Color, icono: Painter){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        modifier = Modifier
            .height(150.dp)
            .width(90.dp)
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Vista previa"

)

@Composable
fun GameResourcesPreview() {
    GreenGuardTheme {
        GameResources()
    }
}