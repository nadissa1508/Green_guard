import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.greenguard.data.model.Tarjeta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _tarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val tarjetas: StateFlow<List<Tarjeta>> = _tarjetas

    private val _proteccionAccionesConservacion = MutableStateFlow(false)
    val proteccionAccionesConservacion: StateFlow<Boolean> = _proteccionAccionesConservacion

    val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> = _toastMessage

    private val _earnedCards = MutableStateFlow<List<Tarjeta>>(emptyList())
    val earnedCards: StateFlow<List<Tarjeta>> = _earnedCards

    private val _currentResources = MutableStateFlow(1000)
    val currentResources: StateFlow<Int> = _currentResources

    private val _userTarjetas = MutableStateFlow<List<Tarjeta>>(emptyList())
    val userTarjetas: StateFlow<List<Tarjeta>> = _userTarjetas

    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    init {
        observeAuthState()
        fetchTarjetas()
    }

    private fun observeAuthState() {
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            _userId.value = auth.currentUser?.uid
            if (auth.currentUser != null) {
                fetchUserTarjetas() // Si hay un usuario, cargar sus tarjetas
            } else {
                _userTarjetas.value = emptyList() // Limpiar las tarjetas del usuario
            }
        }
    }

    fun getCurrentUserUid(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        return user?.uid
    }

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
            saveCardUserFirestore(card)

        }
    }

    fun saveCardUserFirestore(card: Tarjeta) {
        val userId = getCurrentUserUid()
        if (userId == null) {
            Log.e("Firestore", "Usuario no autenticado. No se puede guardar la tarjeta.")
            return
        }

        val cardData = mapOf(
            "idUsuario" to userId,
            "idTarjeta" to card.titulo // Asegúrate de que `titulo` sea único y sirva como ID
        )

        db.collection("tarjetas_usuario")
            .add(cardData) // Agrega un nuevo documento
            .addOnSuccessListener {
                Log.d("Firestore", "Tarjeta guardada exitosamente: ${card.titulo}")
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al guardar la tarjeta", exception)
            }
    }


    fun logout() {
        FirebaseAuth.getInstance().signOut()
        _userId.value = null // Resetea el ID de usuario
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

    fun fetchUserTarjetas() {
        val userId = getCurrentUserUid()
        Log.d("Firestore", "Fetching tarjetas for user: $userId")

        if (userId == null) {
            Log.e("Firestore", "User ID is null")
            return
        }

        db.collection("tarjetas_usuario")
            .whereEqualTo("idUsuario", userId)
            .get()
            .addOnSuccessListener { result ->
                val tarjetaTitulos = result.mapNotNull { it.getString("idTarjeta") }
                Log.d("Firestore", "Tarjeta títulos encontrados: $tarjetaTitulos")

                fetchTarjetasByTitulos(tarjetaTitulos)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error fetching tarjetas_usuario", exception)
            }
    }


    private fun fetchTarjetasByTitulos(titulos: List<String>) {
        if (titulos.isEmpty()) return

        db.collection("tarjetas")
            .whereIn("titulo", titulos)
            .get()
            .addOnSuccessListener { result ->
                val tarjetasList = result.map { document ->
                    document.toObject(Tarjeta::class.java)
                }
                _userTarjetas.value = tarjetasList
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al obtener detalles de tarjetas", exception)
            }
    }


}
