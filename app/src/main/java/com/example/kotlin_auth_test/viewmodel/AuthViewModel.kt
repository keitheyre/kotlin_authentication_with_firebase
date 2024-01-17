import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(
    private val authService: FirebaseAuthService,
    private val userViewModel: UserViewModel
) : ViewModel() {
    // LiveData for user authentication state
    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> = _isUserAuthenticated

    // LiveData for login failure event
    private val _loginFailureEvent = MutableLiveData<Unit>()
    val loginFailureEvent: LiveData<Unit> = _loginFailureEvent

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        _isUserAuthenticated.value = user != null

        // Fetch user profile data if authenticated
        if (user != null) {
            userViewModel.fetchUserProfile(user.uid)
        }
    }

    init {
        // Add the auth state listener when the ViewModel is created
        authService.addAuthStateListener(authStateListener)
    }

    // Function to sign in with email and password
    fun signInWithEmailAndPassword(email: String, password: String) {
        authService.signInWithEmailAndPassword(email, password) { success, errorMessage ->
            if (success) {
                // Sign-in successful, no need to take any further action as authStateListener will handle it
            } else {
                // Sign-in failed, show a Snackbar
                _loginFailureEvent.value = Unit
            }
        }
    }

    // Function to sign out
    fun signOut() {
        authService.signOut()
    }

    // Add other authentication methods...

    override fun onCleared() {
        // Remove the auth state listener when the ViewModel is cleared
        authService.removeAuthStateListener(authStateListener)
        super.onCleared()
    }
}
