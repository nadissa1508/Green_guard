package com.exercise.greenguard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.greenguard.data.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.exercise.greenguard.data.repository.CardRepository

object CardViewModel : ViewModel() {

    private val _tarjetaSeleccionada = MutableStateFlow<Card?>(null)
    val tarjetaSeleccionada: StateFlow<Card?> get() = _tarjetaSeleccionada

    private val repository = CardRepository()

    fun seleccionarTarjetaAleatoria(categoria: String) {
        viewModelScope.launch {
            val tarjetas = repository.obtenerTarjetas() // Traemos todas las tarjetas de Firestore

            // Determina el rango para el id según la categoría
            val rangoId = when (categoria) {
                "desastres naturales" -> 1..5
                "desafios ambientales" -> 6..10
                "acciones de conservacion" -> 11..15
                "eventos positivos" -> 16..20
                else -> null
            }

            // Si el rango es válido, genera un número aleatorio dentro del rango
            rangoId?.let {
                val idAleatorio = it.random()
                // Filtra tarjetas que coincidan con la categoría y el id aleatorio
                val tarjetaFiltrada = tarjetas.firstOrNull { tarjeta ->
                    tarjeta.category == categoria && tarjeta.id == idAleatorio
                }

                // Actualiza el valor de tarjeta seleccionada si se encuentra la tarjeta
                tarjetaFiltrada?.let {
                    _tarjetaSeleccionada.value = it
                }
            }
        }
    }
}
