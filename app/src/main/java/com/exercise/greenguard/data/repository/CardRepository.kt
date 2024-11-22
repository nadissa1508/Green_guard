package com.exercise.greenguard.data.repository

import android.util.Log
import com.exercise.greenguard.data.model.Tarjeta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CardRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun obtenerTarjetas(): List<Tarjeta> {
        return try {
            val snapshot = firestore.collection("tarjetas").get().await()
            snapshot.documents.mapNotNull { it.toObject(Tarjeta::class.java) }
        } catch (e: Exception) {
            emptyList() // Manejo de errores si es necesario
        }
    }
}