package com.exercise.greenguard.view

import CardViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercise.greenguard.R
import com.exercise.greenguard.data.model.Tarjeta
import com.exercise.greenguard.ui.theme.Gray
import com.exercise.greenguard.ui.theme.GreenGuardTheme
import com.exercise.greenguard.ui.theme.Orange
import com.exercise.greenguard.ui.theme.OrangeLight
import com.exercise.greenguard.ui.theme.RedLight
import com.exercise.greenguard.ui.theme.Yellow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items


@Composable
fun GameResources(
    modifier: Modifier = Modifier,
    cuenta: () -> Unit,
    viewModel: CardViewModel = viewModel()
) {
    val fondo = painterResource(id = R.drawable.fondo_juego2)
    val usuario = painterResource(id = R.drawable.usuario)
    val context = LocalContext.current
    val earnedCards by viewModel.earnedCards.collectAsState()
    val idUser by viewModel.userId.collectAsState()
    val currentResources by viewModel.currentResources.collectAsState()
    val toastMessage by viewModel.toastMessage.observeAsState()

    toastMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel._toastMessage.postValue(null)
    }

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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "RECURSOS: $currentResources", // Display updated resources
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    TextButton(
                        onClick = {
                            viewModel.addCardForTurn()
                        },
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Text("Terminar turno", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                if(idUser != null){
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
            }


            Spacer(modifier = Modifier.weight(1f))

            DisplayResourceImages(currentResources)

            // Display the earned cards in a LazyRow
            GameScreen(viewModel = viewModel)


        }
    }
}

@Composable
fun TarjetaSeleccionada(
    modifier: Modifier = Modifier,
    color: Color,
    categoriaIcon: Int,
    titulo: String,
    accion: String,
    recursos: String,
    onInfoClick: () -> Unit,
    viewModel: CardViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .width(180.dp)
            .height(570.dp)
            .padding(5.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.End)
                        .clickable { onInfoClick() },
                )
                Text(
                    text = titulo,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = accion,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )

                // Ícono de chequecito y de categoria
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (categoriaIcon == R.drawable.icon_conservacion) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_si),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    when (categoriaIcon) {
                                        R.drawable.icon_conservacion -> viewModel.usarTarjetaConservacion(recursos)
                                    }
                                }

                        )
                    }

                    Image(
                        painter = painterResource(id = categoriaIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
            }
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
fun DisplayResourceImages(currentResources: Int) {
    val resourceImages = mutableListOf<Int>()

    // Determine how many images to show based on current resources
    var remainingResources = currentResources

    // Add trees (100 resources each)
    while (remainingResources >= 100) {
        resourceImages.add(R.drawable.arbol)
        remainingResources -= 100
    }

    // Add corn (50 resources each)
    while (remainingResources >= 50) {
        resourceImages.add(R.drawable.elotes)
        remainingResources -= 50
    }

    // Add deer (25 resources each)
    while (remainingResources >= 25) {
        resourceImages.add(R.drawable.venado)
        remainingResources -= 25
    }

    // Add flowers (10 resources each)
    while (remainingResources >= 10) {
        resourceImages.add(R.drawable.flores) // or R.drawable.arbusto depending on your design
        remainingResources -= 10
    }

    // Set a fixed height for the grid
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp) // Set the desired height here
            .padding(vertical = 16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Change the number of columns as needed
            content = {
                items(resourceImages) { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp) // Adjust size as needed
                    )
                }
            }
        )
    }
}

@Composable
fun GameScreen(viewModel: CardViewModel = viewModel()) {
    val earnedCards by viewModel.earnedCards.collectAsState()
    val showDescripcionDialog = remember { mutableStateOf(false) }
    var selectedTarjeta by remember { mutableStateOf<Tarjeta?>(null) }

    // Make the row scrollable by using LazyRow
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
    ) {
        items(earnedCards) { tarjeta ->
            TarjetaSeleccionada(
                color = when (tarjeta.categoria) {
                    "desastres naturales" -> RedLight
                    "desafios ambientales" -> Orange
                    "acciones de conservacion" -> OrangeLight
                    "eventos positivos" -> Yellow
                    else -> Gray
                },
                categoriaIcon = when (tarjeta.categoria) {
                    "desastres naturales" -> R.drawable.icon_desastres
                    "desafios ambientales" -> R.drawable.icon_desafios
                    "acciones de conservacion" -> R.drawable.icon_conservacion
                    "eventos positivos" -> R.drawable.icon_ev_positivos
                    else -> R.drawable.icon_desastres
                },
                titulo = tarjeta.titulo,
                accion = tarjeta.accion,
                recursos = tarjeta.recursos,
                onInfoClick = {
                    selectedTarjeta = tarjeta
                    showDescripcionDialog.value = true
                }
            )
        }
    }

    if (showDescripcionDialog.value) {
        selectedTarjeta?.let { tarjeta ->
            DescripcionDialog(
                titulo = tarjeta.titulo,
                descripcion = tarjeta.descripcion,
                onDismissRequest = { showDescripcionDialog.value = false }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GreenGuardTheme {
        GameScreen()
    }
}
