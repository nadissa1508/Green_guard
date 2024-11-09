package com.exercise.greenguard.data.repository

import android.util.Log
import com.exercise.greenguard.data.model.Card
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CardRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun obtenerTarjetas(): List<Card> {
        return try {
            val snapshot = firestore.collection("tarjetas").get().await()
            snapshot.documents.mapNotNull { it.toObject(Card::class.java) }
        } catch (e: Exception) {
            emptyList() // Manejo de errores si es necesario
        }
    }
}