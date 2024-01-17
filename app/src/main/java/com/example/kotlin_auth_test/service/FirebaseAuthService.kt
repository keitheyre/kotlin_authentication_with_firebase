import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Function to add an auth state listener
    fun addAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.addAuthStateListener(listener)
    }

    // Function to remove an auth state listener (optional but recommended to prevent memory leaks)
    fun removeAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.removeAuthStateListener(listener)
    }

    // Function to check if a user is currently authenticated
    fun isUserLoggedIn(): Boolean {
        val currentUser: FirebaseUser? = auth.currentUser
        return currentUser != null
    }

    // Function to get the current authenticated user
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    // Function to sign in with email and password
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null) // Sign-in successful
                } else {
                    val errorMessage = task.exception?.message ?: "Sign-in failed"
                    onComplete(false, errorMessage) // Sign-in failed with error message
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

    // Add other authentication methods (e.g., sign-up, password reset) as needed
}
