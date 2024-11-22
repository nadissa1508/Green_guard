package com.exercise.greenguard.viewmodel

import androidx.lifecycle.ViewModel
import com.exercise.greenguard.data.model.CardUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CardUserViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()
}