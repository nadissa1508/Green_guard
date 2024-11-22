package com.exercise.greenguard

import CardViewModel
import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.exercise.greenguard.data.repository.CardRepository
import com.exercise.greenguard.ui.theme.GreenGuardTheme
import com.exercise.greenguard.view.CuentaApp
import com.exercise.greenguard.view.GameResources
import com.exercise.greenguard.view.LogInScreen
import com.exercise.greenguard.view.RegistroApp
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

enum class GreenGuardScreen (@StringRes val title: Int){
    Inicio (title = R.string.Inicio),
    Juego (title = R.string.Juego),
    Cuenta (title = R.string.Cuenta),
    Login (title = R.string.Login),
    Registro (title = R.string.Registro)
}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenGuardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    GreenGuardApp()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // Llamar a logout cuando la app se va a segundo plano o se cierra
        val viewModel: CardViewModel = ViewModelProvider(this)[CardViewModel::class.java]
        viewModel.logout()
    }

    override fun onDestroy() {
        super.onDestroy()
        val viewModel: CardViewModel = ViewModelProvider(this)[CardViewModel::class.java]
        if (isFinishing) { // Solo realiza logout si la actividad se está cerrando completamente
            viewModel.logout()

        }
    }
}

@Composable
fun GreenGuardApp(){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GreenGuardScreen.valueOf(
        backStackEntry?.destination?.route ?: GreenGuardScreen.Inicio.name
    )
    Scaffold(
        topBar = {
//            AppTopBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GreenGuardScreen.Inicio.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = GreenGuardScreen.Inicio.name) {
                MenuElements(
                    siguiente = { navController.navigate(GreenGuardScreen.Login.name) },
                    juego = { navController.navigate(GreenGuardScreen.Juego.name) }
                )
            }
            composable(route = GreenGuardScreen.Login.name) {
                LogInScreen(
                    siguiente = { navController.navigate(GreenGuardScreen.Registro.name) },
                    juego = { navController.navigate(GreenGuardScreen.Juego.name) }
                )
            }
            composable(route = GreenGuardScreen.Registro.name) {
                RegistroApp(
                    siguiente = { navController.navigate(GreenGuardScreen.Login.name) }
                )
            }

            composable(route = GreenGuardScreen.Juego.name) {
                GameResources(
                    cuenta = { navController.navigate(GreenGuardScreen.Cuenta.name) },
                )
            }

            composable (route = GreenGuardScreen.Cuenta.name){
                CuentaApp()
            }
        }
    }
}