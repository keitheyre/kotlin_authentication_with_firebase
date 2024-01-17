import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(private val authService: FirebaseAuthService) : ViewModel() {
    // LiveData for user authentication state
    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> = _isUserAuthenticated

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        _isUserAuthenticated.value = user != null
    }

    init {
        // Add the auth state listener when the ViewModel is created
        authService.addAuthStateListener(authStateListener)
    }

    // Function to sign in with email and password
    fun signInWithEmailAndPassword(email: String, password: String) {
        // Implementation...
    }

    // Function to sign out
    fun signOut() {
        // Implementation...
    }

    // Add other authentication methods...

    override fun onCleared() {
        // Remove the auth state listener when the ViewModel is cleared
        authService.removeAuthStateListener(authStateListener)
        super.onCleared()
    }
}
