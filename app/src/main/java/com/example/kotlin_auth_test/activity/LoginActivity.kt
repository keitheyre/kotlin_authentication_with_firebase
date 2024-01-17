import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.keitheyre.kotlin_auth_test.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe the login failure event
        authViewModel.loginFailureEvent.observe(this, Observer {
            // Show a Snackbar when login fails
            Snackbar.make(
                binding.root,
                "Login failed. Please check your credentials.",
                Snackbar.LENGTH_SHORT
            ).show()
        })

        binding.loginButton.setOnClickListener {
            // Retrieve email and password from the input fields
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Call the signInWithEmailAndPassword function to attempt login
            authViewModel.signInWithEmailAndPassword(email, password)
        }

        // Add your other UI setup and logic here
    }

    // Add the rest of your LoginActivity code here
}
