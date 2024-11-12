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
        // Select a random card to simulate the game giving a new card
        val randomCard = tarjetas.value.randomOrNull()
        randomCard?.let { card ->
            _earnedCards.update { it + card }
            // Update resources by adding the 'recursos' field from the card
            _currentResources.update { it + (card.recursos as? Int ?: 0) }
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
