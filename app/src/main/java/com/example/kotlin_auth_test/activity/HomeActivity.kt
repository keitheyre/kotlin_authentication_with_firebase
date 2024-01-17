import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val firestoreService = FirestoreService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize FirestoreService
        firestoreService.init()

        // Fetch the user's data from Firestore
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            GlobalScope.launch(Dispatchers.IO) {
                val userProfile = firestoreService.getUserProfile(user.uid)
                if (userProfile != null) {
                    // Update UI with the user's data
                    runOnUiThread {
                        binding.userNameTextView.text = "Welcome, ${userProfile.username}"
                    }
                }
            }
        }

        // Add more UI interactions or functionality as needed
    }
}
