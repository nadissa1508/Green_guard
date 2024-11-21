import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.greenguard.data.model.Tarjeta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    private val _proteccionAccionesConservacion = MutableStateFlow(false)
    val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> = _toastMessage

    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas
    val proteccionAccionesConservacion: StateFlow<Boolean> = _proteccionAccionesConservacion

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

            var recursosValue = card.recursos.toIntOrNull() ?: 0
            val categoriaTarjeta = card.categoria

            if(_proteccionAccionesConservacion.value && categoriaTarjeta == "desafios ambientales" ){
                _proteccionAccionesConservacion.value = false
                recursosValue /= 2
                _toastMessage.postValue("¡Enhorabuena! Solo perdiste la mitad de los recursos")
            }
            if(categoriaTarjeta != "acciones de conservacion"){
                _currentResources.update { it + recursosValue }
            }

        }
    }

    fun usarTarjetaConservacion(recursos: String) {
        _proteccionAccionesConservacion.value = true
        var restarRecursos = recursos.toIntOrNull()  ?: 0
        _currentResources.update { it + restarRecursos }

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
