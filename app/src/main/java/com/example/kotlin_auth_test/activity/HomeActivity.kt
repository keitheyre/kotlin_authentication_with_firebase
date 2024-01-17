import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.keitheyre.kotlin_auth_test.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Observe the user profile LiveData
        userViewModel.userProfile.observe(this) { userProfile ->
            if (userProfile != null) {
                // Update UI with the user's name
                binding.userNameTextView.text = "Welcome, ${userProfile.name}"
            }
        }

        // Add more UI interactions or functionality as needed
    }
}
