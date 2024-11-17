import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.greenguard.data.model.Tarjeta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas

    private val _earnedCards = MutableStateFlow<List<Tarjeta>>(emptyList())
    val earnedCards: StateFlow<List<Tarjeta>> = _earnedCards

    init {
        fetchTarjetas()
    }

    // Initialize resources with 1000
    private val _currentResources = MutableStateFlow(1000)
    val currentResources: StateFlow<Int> = _currentResources

    fun addCardForTurn() {
        val randomCard = tarjetas.value.randomOrNull()
        randomCard?.let { card ->
            _earnedCards.update { it + card }

            val recursosValue = card.recursos.toIntOrNull() ?: 0
            _currentResources.update { it + recursosValue }
        }
    }

    fun fetchTarjetas() {
        db.collection("tarjetas")
            .get()
            .addOnSuccessListener { result ->
                val tarjetasList = result.map { document ->
                    document.toObject(Tarjeta::class.java)
                }
                _tarjetas.value = tarjetasList
            }
            .addOnFailureListener { exception ->
                // Handle any errors here
            }
    }


}
