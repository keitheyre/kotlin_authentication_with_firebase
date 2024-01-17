import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_auth_test.model.UserProfile
import com.google.firebase.auth.FirebaseAuth

class UserViewModel(private val firestoreService: FirestoreService) : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val uid = auth.currentUser?.uid

    // LiveData for user profile data
    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: MutableLiveData<UserProfile?> = _userProfile

    init {
        if (uid != null) {
            fetchUserProfile(uid)
        }
    }

    private fun fetchUserProfile(uid: String) {
        firestoreService.getUserProfile(uid)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                    if (userProfile != null) {
                        _userProfile.value = userProfile
                    }
                }
            }
            .addOnFailureListener { e ->
                // Handle the error here
            }
    }
}
